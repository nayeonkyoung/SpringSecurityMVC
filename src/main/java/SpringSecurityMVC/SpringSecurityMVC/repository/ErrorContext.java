/*******************************************************************************
 * This program and the accompanying materials are made
 * available under the terms of the Inzent MCA License v1.0
 * which accompanies this distribution.
 * 
 * Contributors:
 *     Inzent Corporation - initial API and implementation
 *******************************************************************************/
package SpringSecurityMVC.SpringSecurityMVC.repository ;

import java.io.Serializable ;

/**
 * <code>ErrorContext</code>
 * 
 * @since 2013. 5. 7.
 * @version 1.x
 * @author 300547
 */
public class ErrorContext implements Serializable
{
  private static final long serialVersionUID = -2743417422569556464L ;

  private String field ;
  private String className ;
  private String message ;
  private String stackTrace ;

  public String getField()
  {
    return field ;
  }
  public void setField(String field)
  {
    this.field = field ;
  }

  public String getClassName()
  {
    return className ;
  }
  public void setClassName(String className)
  {
    this.className = className ;
  }

  public String getMessage()
  {
    return message ;
  }
  public void setMessage(String message)
  {
    this.message = message ;
  }

  public String getStackTrace()
  {
    return stackTrace ;
  }
  public void setStackTrace(String stackTrace)
  {
    this.stackTrace = stackTrace ;
  }

  @Override
  public String toString()
  {
    return new StringBuilder().append(message).append(System.lineSeparator()).append(stackTrace).toString() ;
  }
}
