YUI.add('helloworldTest', function(Y) {
		
		//create the test suite
		var suite = new Y.Test.Suite("HelloworldTest");

		//add test cases
		var testCase = new Y.Test.Case({

			name: "HelloworldTest",
			"HelloworldTest test": function () {
				var helloNode = Y.Node.create('<div></div>');
				var appAPI = new Y.Base.AppAPI();
				
				// instantiate NavigationWidget with the HTML
				var helloWidget = new Y.Base.HelloWidget({
					// place holder can be decided by specifying the attribute
					targetNode : helloNode,
					apiReference : appAPI
			   });

				helloWidget.renderUI();
				verifyTxt = helloWidget.get('targetNode');
				
				Y.Assert.areEqual(helloWidget.getTargetNode().get('text'), "Hello World", "Helloworld Success case");
			}
			
		});
		
		suite.add(testCase);
		Y.Test.Runner.add(suite);
	});