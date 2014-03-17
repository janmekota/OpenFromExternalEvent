OpenFromExternalEvent
=====================

Eclipse plugin which can handle external event and open files or show a stacktrace.
This feature is inspired by 2 features:
* the Direct Click Through feature of the Atlassian Connector
* Eclipse [Open from Clipboard feature][1]

## How does it work?

It's really quite simple. Eclipse will listen on a port locally on the client machine (it'll only accept loopback IPs, so there is no security breach). Your link is really a simple call to this port. Eclipse plugin will pick this up and work magic.

## What is handled

Tries to **open the matching** Java element in the editor if the clipboard contains a **single line**. **Otherwise** it opens the contents in the **Java Stack Trace Console**. Examples:

    java.lang.String
    String
    String#getBytes
    String.getBytes
    java.lang.String.getBytes(String)
    String.java:123
    at java.lang.String.matches(String.java:1550)
    java.lang.String.valueOf(char) line: 1456
    currentTimeMillis()

## Plugin installation

To install the Eclipse plugin:

* In Eclipse, click on Help -> Install New Software...
* Click on Add..
* Enter the following:
  Name: Open from External Event plugin
  URL: http://cbos.github.io/OpenFromExternalEvent/update/
  and click OK.
* Select the plugin and click Next >.
* You'll need to accept the (empty) license and confirm you want to install a plugin that is not digitally signed. Go ahead and install it anyway.
* Restart eclipse.


  [1]: http://help.eclipse.org/indigo/index.jsp?topic=/org.eclipse.jdt.doc.user/reference/ref-menu-navigate.htm