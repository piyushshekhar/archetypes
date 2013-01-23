<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Properties" %>			
<%@ page import="com.photon.phresco.configuration.ConfigReader" %>
<%@ page import="com.photon.phresco.configuration.Configuration" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Hello World</title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <!-- Linking styles -->
        	
           <!--  Style sheets -->
           <style>
				#helloworld{
					margin:0 auto;
					width:80%;
					text-align:center;
					font-weight:bold;
					font-size:30px;
					padding:30% 0 35% 0;					
					color:#00000;
				}
				
				html, body {
					height:100%;
					margin:0;
					padding:0;
				}
			</style>
		 <script type="text/javascript" src="lib/jslibraries/files/jslib_almond/0.0.2-alpha-1/jslib_almond-0.0.2-alpha-1.js"></script>
        <script type="text/javascript" charset="utf-8" src="lib/jslibraries/files/jslib_jquery-amd/1.7.1-alpha-1/jslib_jquery-amd-1.7.1-alpha-1.js"></script>
        <script type="text/javascript" src="lib/yui/build/yui/yui-min.js"></script>
		
		
			
		<%
			String currentEnv = System.getProperty("SERVER_ENVIRONMENT");
			if (currentEnv == null) {
				currentEnv = "Production";
			}
			String path = application.getRealPath("/WEB-INF/resources/phresco-env-config.xml");
			File file = new File(path);
			ConfigReader reader = new ConfigReader(file);
			String configJson = reader.getConfigAsJSON(currentEnv, "WebService");
		%>

		
        <script type="text/javascript" src="js/eshop/controller/appAPI.js"></script>

        <script type="text/javascript" src="js/eshop/widgets/headerWidget.js"></script>
        <script type="text/javascript" src="js/eshop/widgets/loginWidget.js"></script>

        <script type="text/javascript">
			var configJson = '<%= configJson %>';
			var currentEnv = '<%= currentEnv %>';
            YUI().use('node', 'widget', 'appAPI', 'headerWidget', 'loginWidget', function(Y) {

                Y.on("domready", function () {
                    var appAPI = new Y.Base.AppAPI(($.parseJSON(configJson)),currentEnv);

                   // instantiate NavigationWidget with the HTML
                    var LoginWidget = new Y.Base.LoginWidget({
                        // place holder can be decided by specifying the attribute
                        targetNode : "#helloworld",
						apiReference : appAPI
                   });

                  //  appAPI.getWSConfig();
                    //helloAPI.getConfig();
                    LoginWidget.renderUI();
                });
            });
        </script>
    </head>
    <body>
		<div id="helloworld"></div>
    </body>
</html>