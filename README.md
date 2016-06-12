# groceryscrapper

Scenario 1: Sainsbury Ripe and Ready link Should return json string containing product properties
	Given that A user types Fresh fruit link
	When he uses Ripe and Ready url 
	Then It should return a json string with title, unit_price, size and description
	And total field which is a sum of all unit prices on the page.

Requires :
	Java 1.7 or above
	Maven
	Dependencies : Apache httpclient, jsoup, guava, gson, slf4j with log4j

How to set up on local machine:
	Git clone
	Run 'mvn clean install' from project root folder

How to run unit test:
	Run the following command from project root folder
		mvn test
		   
How to execute:
	Create the jar file using the following command (Created at Project Root/target folder)
		mvn clean install
	Run the following to verify
		java -jar scrapperExercise-jar-with-dependencies.jar http://www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/ripe---ready

Expect:
	A json string - 
	DEBUG [main] (GroceryApplication.java:60) - Json String 
	{"results": [{"title": "Sainsbury's Apricot Ripe & Ready x5","size": "39.9 kb", "unitPrice": 3.0, "description": "Apricots" },
    			 {"title": "Sainsbury's Avocado Ripe & Ready XL Loose 300g", "size": "40.9 kb","unitPrice": 1.5, "description": "Avocados"},
    			 {"title": "Sainsbury's Avocado, Ripe & Ready x2","size": "45.7 kb","unitPrice": 1.8, "description": "Avocados"},
    			 {"title": "Sainsbury's Avocados, Ripe & Ready x4","size": "40.3 kb","unitPrice": 3.2,"description": "Avocados"},
    			 {"title": "Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)","size": "40.2 kb","unitPrice": 1.75,"description": "Conference"},
    			 {"title": "Sainsbury's Golden Kiwi x4","size": "40.2 kb","unitPrice": 1.65,"description": "Gold Kiwi"},
    			 {"title": "Sainsbury's Kiwi Fruit, Ripe & Ready x4","size": "40.6 kb","unitPrice": 1.65,"description": "Kiwi"},
    			 {"title": "Sainsbury's Kiwi Fruit, SO Organic x4","size": "40.5 kb","unitPrice": 1.0,"description": "Kiwi"},
    			 {"title": "Sainsbury's Mango, Ripe & Ready x2 Only £1.75: Save 25p","size": "41.3 kb","unitPrice": 1.75,"description": "by Sainsbury's Ripe and Ready Mango"},
    			 {"title": "Sainsbury's Nectarines, Ripe & Ready x4","size": "36.8 kb","unitPrice": 2.0,"description": "Description"},
    			 {"title": "Sainsbury's Papaya, Ripe (each)","size": "40.4 kb","unitPrice": 1.5,"description": "Papaya"},
    			 {"title": "Sainsbury's Pears, Ripe & Ready x4 (minimum)","size": "40.2 kb","unitPrice": 1.75,"description": "Pear"},
    			 {"title": "Sainsbury's Ripe & Ready Red Pears x4","size": "40.2 kb","unitPrice": 1.75,"description": "Description Sweet & Juicy Blush Pears Ripe & ready Sweet & Juicy One pear counts as 1 of your 5 a-day One pear counts as 1 of your 5 a-day Aim for at least 5 different portions of fruit or veg a day. Fresh, frozen, dried, canned and juice all count. We value the quality of fresh fruit and vegetables We are proud to work in partnership with our suppliers, who participate in an independent audit programme that ensures responsible pesticide usage and encourages environmental protection and wildlife conservation."
    		}],
  	"total": 24.3
	} 	
	
TODO:
	customised exception handling
	Integration tests
	Test cased for HTTP Service implementaion
