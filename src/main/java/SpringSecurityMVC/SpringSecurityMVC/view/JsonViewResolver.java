/*******************************************************************************
 * This program and the accompanying materials are made
 * available under the terms of the Inzent MCA License v1.0
 * which accompanies this distribution.
 *
 * Contributors:
 * Inzent Corporation - initial API and implementation
 *******************************************************************************/
package SpringSecurityMVC.SpringSecurityMVC.view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.JsonEncoding;
import SpringSecurityMVC.SpringSecurityMVC.common.JsonMarshaller;
import SpringSecurityMVC.SpringSecurityMVC.controller.BaseController;
import SpringSecurityMVC.SpringSecurityMVC.repository.ErrorContext;

/**
 * <code>JsonViewResolver</code>
 *
 * @since 2014. 12. 4.
 * @version 4.4
 * @author Jaesuk
 */
public class JsonViewResolver extends AbstractView implements ViewResolver {

	public JsonViewResolver() {
		this.setContentType(MappingJackson2JsonView.DEFAULT_CONTENT_TYPE);
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(this.getContentType());
		response.setCharacterEncoding(JsonEncoding.UTF8.getJavaName());

		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
	}

	@Override
	protected Map<String, Object> createMergedOutputModel(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>(model.size());
		Object value;

		result.put(ClassUtils.getShortNameAsProperty(ViewMode.class),
				model.get(ClassUtils.getShortNameAsProperty(ViewMode.class)));
		result.put(BaseController.MODEL_OBJECT, model.get(BaseController.MODEL_OBJECT));

		value = model.get(BaseController.MODEL_ERROR);
		if (null != value) {
			if (value instanceof Throwable) {
				ErrorContext errorContext = new ErrorContext();

				if (value instanceof CredentialsExpiredException) {
					errorContext.setField("CredentialsExpired");
				}

				errorContext.setClassName(value.getClass().getName());
				errorContext.setMessage(((Throwable) value).getMessage());

				value = new ErrorContext[] { errorContext };
			}
		} else {
			for (Map.Entry<String, ?> entry : model.entrySet()) {
				if (entry.getValue() instanceof BindingResult) {
					BindingResult bindingResult = (BindingResult) entry.getValue();

					LinkedList<ErrorContext> errorContexts = new LinkedList<ErrorContext>();
					ErrorContext errorContext;

					for (ObjectError objectError : bindingResult.getGlobalErrors()) {
						errorContext = new ErrorContext();
						errorContext.setMessage(objectError.getDefaultMessage());

						errorContexts.add(errorContext);
					}

					for (FieldError fieldError : bindingResult.getFieldErrors()) {
						errorContext = new ErrorContext();
						errorContext.setField(fieldError.getField());
						errorContext.setMessage(fieldError.getDefaultMessage());

						errorContexts.add(errorContext);
					}

					if (!errorContexts.isEmpty()) {
						value = errorContexts;
					}
				}
			}
		}

		if (null != value) {
			result.put("result", "error");
			result.put(BaseController.MODEL_ERROR, value);
		} else {
			result.put("result", "ok");
			result.put(BaseController.MODEL_RESPONSE, model.get(BaseController.MODEL_RESPONSE));
		}

		return super.createMergedOutputModel(result, request, response);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte[] body = JsonMarshaller.marshalToBytes(model);

		response.setContentLength(body.length);

		ServletOutputStream os = response.getOutputStream();

		os.write(body);
		os.flush();
	}

	public View resolveViewName(String viewName, Locale locale) throws Exception {
		return viewName.endsWith(".json") ? this : null;
	}

}
