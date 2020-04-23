/*******************************************************************************
 * This program and the accompanying materials are made
 * available under the terms of the Inzent MCA License v1.0
 * which accompanies this distribution.
 * 
 * Contributors:
 *     Inzent Corporation - initial API and implementation
 *******************************************************************************/
package SpringSecurityMVC.SpringSecurityMVC.common ;

import java.io.IOException ;
import java.text.SimpleDateFormat ;


import com.fasterxml.jackson.annotation.JsonInclude.Include ;
import com.fasterxml.jackson.core.JsonProcessingException ;
import com.fasterxml.jackson.core.io.SegmentedStringWriter ;
import com.fasterxml.jackson.core.type.TypeReference ;
import com.fasterxml.jackson.databind.DeserializationFeature ;
import com.fasterxml.jackson.databind.JsonNode ;
import com.fasterxml.jackson.databind.ObjectMapper ;
import com.fasterxml.jackson.databind.SerializationFeature ;
import com.fasterxml.jackson.databind.introspect.Annotated ;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector ;

/**
 * <code>JsonMarshaller</code>
 *
 * @since 2014. 12. 12.
 * @version 5.0
 * @author Jaesuk
 */
public class JsonMarshaller
{
  public static final ObjectMapper objectMapper ;
  public static final ObjectMapper objectDump ;

  static
  {
    objectMapper = new ObjectMapper() ;

    objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector()
    {
      private static final long serialVersionUID = -3297823858713826907L ;

      @Override
      protected boolean _isIgnorable(Annotated annotated)
      {
        if (super._isIgnorable(annotated))
          return true ;
        else
          return false;
      }
    }) ;
    objectMapper.setSerializationInclusion(Include.NON_ABSENT) ;
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z")) ;
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) ;
    objectMapper.disable(SerializationFeature.INDENT_OUTPUT) ;

    objectDump = new ObjectMapper() ;

    objectDump.setAnnotationIntrospector(new JacksonAnnotationIntrospector()
    {
      private static final long serialVersionUID = -5655907890166362415L ;

      @Override
      protected boolean _isIgnorable(Annotated annotated)
      {
        if (super._isIgnorable(annotated))
          return true ;
        else
          return false;
      }
    }) ;
    objectDump.setSerializationInclusion(Include.NON_ABSENT) ;
    objectDump.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z")) ;
    objectDump.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) ;
    objectDump.enable(SerializationFeature.INDENT_OUTPUT) ;
  }

  public static byte[] marshalToBytes(Object value)
  {
    try
    {
      return objectMapper.writeValueAsBytes(value) ;
    }
    catch (JsonProcessingException je)
    {
      throw new RuntimeException(je) ;
    }
  }

  public static String marshalToString(Object value)
  {
    try
    {
      return objectMapper.writeValueAsString(value) ;
    }
    catch (JsonProcessingException je)
    {
      throw new RuntimeException(je) ;
    }
  }

  public static JsonNode marshalToJsonNode(Object value)
  {
    return objectMapper.valueToTree(value) ;
  }

  public static <T> T unmarshal(String content, Class<T> valueType) throws IOException
  {
    return objectMapper.readValue(content, valueType) ;
  }

  public static <T> T unmarshal(String content, TypeReference<T> valueTypeRef) throws IOException
  {
    return objectMapper.readValue(content, valueTypeRef) ;
  }

  public static <T> T unmarshal(byte[] src, Class<T> valueType) throws IOException
  {
    return objectMapper.readValue(src, valueType) ;
  }

  public static <T> T unmarshal(byte[] src, TypeReference<T> valueTypeRef) throws IOException
  {
    return objectMapper.readValue(src, valueTypeRef) ;
  }

  public static <T> T unmarshal(JsonNode jsonNode, Class<T> valueType) throws IOException
  {
    return objectMapper.treeToValue(jsonNode, valueType) ;
  }

  public static <T> T unmarshal(JsonNode jsonNode, TypeReference<T> valueTypeRef) throws IOException
  {
    return objectMapper.readValue(objectMapper.treeAsTokens(jsonNode), valueTypeRef) ;
  }

  public static String dumpObject(JsonNode rootNode)
  {    
    SegmentedStringWriter sw = new SegmentedStringWriter(objectDump.getFactory()._getBufferRecycler()) ;
    try
    {
      objectDump.writeTree(objectDump.getFactory().createGenerator(sw), rootNode) ;
    }
    catch (IOException ie)
    {
      throw new RuntimeException(ie) ;
    }

    return sw.getAndClear() ;
  }

  public static String dumpObject(Object value)
  {
    try
    {
      return objectDump.writeValueAsString(value) ;
    }
    catch (JsonProcessingException je)
    {
      throw new RuntimeException(je) ;
    }
  }
}
