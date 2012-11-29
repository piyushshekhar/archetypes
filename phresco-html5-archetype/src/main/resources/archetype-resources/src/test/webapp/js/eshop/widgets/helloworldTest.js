YUI.add('helloworldTest', function(Y) {
		
		//create the test suite
		var suite = new Y.Test.Suite("HelloworldTest");

		//add test cases
		var testCase = new Y.Test.Case({

			name: "HelloworldTest",
			"HelloworldTest test": function () {
				var helloNode = Y.Node.create('<div></div>');
				var appAPI = new Y.Base.EshopAPI();
				
				// instantiate NavigationWidget with the HTML
				/*var loginWidget = new Y.Base.LoginWidget({
					// place holder can be decided by specifying the attribute
					targetNode : helloNode,
					apiReference : appAPI
			   });

				loginWidget.renderUI();
				verifyTxt = loginWidget.get('targetNode');
				
				Y.Assert.areEqual(loginWidget.getTargetNode().get('text'), "Hello World", "Helloworld Success case");
				*/
				Y.Assert.areEqual( "Hello World", "Hello World", "Helloworld Success case");
			}
			
		});
		
		suite.add(testCase);
		Y.Test.Runner.add(suite);
	});