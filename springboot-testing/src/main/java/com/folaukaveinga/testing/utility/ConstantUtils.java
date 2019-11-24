package com.folaukaveinga.testing.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.folaukaveinga.testing.user.User;

public final class ConstantUtils {

	public static List<String> goodCreditCards = Arrays.asList("4242424242424242", "4000056655665556",
			"5555555555554444", "2223003122003222", "5200828282828210", "5105105105105100", "378282246310005",
			"371449635398431", "6011111111111117", "6011000990139424", "30569309025904", "38520000023237",
			"3566002020360505", "6200000000000005");

	private static final List<String> genders = Arrays.asList("MALE", "FEMALE");

	private static final List<String> COMPANIES = Arrays.asList("Apple", "Microsoft", "Intel", "IBM", "Facebook",
			"Walmart", "Samsung", "Costco", "Food for Less", "One Tree", "Dollar Tree", "Taco Bell", "Hair Salon");

	private static final List<String> initialPaymentOptions = Arrays.asList("monthly", "quarterly", "semiAnnual",
			"annual");

	private static final List<String> relationOptions = Arrays.asList("SPOUSE", "DEPENDENT");

	public static final List<String> careCenterOptions = Arrays.asList("CVS Pharmacy", "Drug Mart", "Family Pharmacy",
			"Walmart Stores, Inc", "Rite Aid", "Walgreens Alliance", "CVS Health", "Garden Hospital", "Pine Clinic",
			"Grand Hospital", "Paradise Hospital", "Bayhealth Center", "Oak Medical Center", "Lullaby Med-Center",
			"Guardian Hospital", "Mount-View Hospital", "Johns Hopkins", "Mayo Clinic", "Massachusetts General",
			"Texas Med-Center", "UCSF Med-Center", "UCLA Medical Center");

	public static List<ObjectNode> TEXAS_ADDRESSES = null;

	public static List<ObjectNode> ARIZON_ADDRESSES = null;

	public static List<ObjectNode> CALIFORNIA_ADDRESSES = null;

	public static List<ObjectNode> ARKANSAS_ADDRESSES = null;

	public static List<ObjectNode> FLORIDA_ADDRESSES = null;

	public static final String password = "Test1234!";

	// can add as payment method but can't charge
	public static String badCard = "4000000000000341";

	private ConstantUtils() {
	}

	public static String getGoodCardNumbers() {
		return goodCreditCards.get(RandomGeneratorUtils.getIntegerWithin(0, goodCreditCards.size() - 1));
	}

	public static String getInvalidCardNumbers() {
		// Following are bad card numbers. Stripe will reject them.
		return "4000000000000341"; // Card declined - Attaching this card to a Customer object succeeds, but an
									// attempt to charge the customer fail.
//		return "4000000000000069"; // Charge is declined with an expired_card code.
	}

	public static String getBadCard() {
		return badCard;
	}

	public static String getRandomInitialPaymentOption() {
		return getRandomInitialPaymentOption(RandomGeneratorUtils.getIntegerWithin(0, initialPaymentOptions.size()));
	}

	public static String getRandomInitialPaymentOption(int index) {
		return ConstantUtils.initialPaymentOptions.get(index);
	}

	public static String getRandomFirstname() {
		List<String> randomFirstNames = ConversionUtil.getListFromSet(ConversionUtil.getSetFromList(firstnames));
		return randomFirstNames.get(RandomGeneratorUtils.getIntegerWithin(0, randomFirstNames.size() - 1));
	}

	public static String getRandomLastname() {
		List<String> randomLastNames = ConversionUtil.getListFromSet(ConversionUtil.getSetFromList(lastnames));
		return randomLastNames.get(RandomGeneratorUtils.getIntegerWithin(0, randomLastNames.size() - 1));
	}

	public static String getRandomHealthCenter() {
		List<String> randomCareCenters = ConversionUtil
				.getListFromSet(ConversionUtil.getSetFromList(careCenterOptions));
		return randomCareCenters.get(RandomGeneratorUtils.getIntegerWithin(0, randomCareCenters.size() - 1));
	}

	public static String getRandomGender() {
		return ConstantUtils.genders.get(RandomGeneratorUtils.getIntegerWithin(0, ConstantUtils.genders.size()));
	}

	public static String getRandomRelation() {
		return ConstantUtils.relationOptions
				.get(RandomGeneratorUtils.getIntegerWithin(0, ConstantUtils.relationOptions.size()));
	}

	public static User generateUser() {
		User user = new User();
		
		int oldestAge = 40;
		user.setDob(DateTimeUtils.getRandomDob(oldestAge));
		user.setEmail(getRandomEmail());
		user.setFirstName(getRandomFirstname());
		user.setLastName(getRandomLastname());
		user.setGender(getRandomGender());
		user.setPhoneNumber(getRandomPhoneNumber());
		
		String ssn = getSSN();
		
		user.setSsn(ssn);
		user.setSsnLast4(ssn.substring(ssn.length()-4, ssn.length()));
		return user;
	}

	private static String getRandomPhoneNumber() {
		return "310"+RandomGeneratorUtils.getIntegerWithin(100, 999)+""+RandomGeneratorUtils.getIntegerWithin(1000, 9999);
	}

	public static ObjectNode getValidSSNAddress() {
		ObjectNode address = ObjectUtils.getObjectNode();
		address.put("street", "2625 Old Denton Road");
		address.put("city", "Carrollton");
		address.put("state", "TX");
		address.put("zip", "75007");
		address.put("lon", -96.91097579999996);
		address.put("lat", 32.9855901);
		return address;
	}

	public static String getRandomEmail() {
		return getRandomEmail("folaudev");
	}

	public static String getRandomEmail(String name) {
		return getRandomEmail(name, null);
	}

	public static String getRandomEmail(String name, String slug) {
		String email = null;
		if (slug == null) {
			email = name +"+"+ RandomGeneratorUtils.getIntegerWithin(1, Integer.MAX_VALUE - 1) + "@gmail.com";
		} else {
			email = name + slug +"+"+ RandomGeneratorUtils.getIntegerWithin(1, Integer.MAX_VALUE - 1)
					+ "@gmail.com";
		}
		return email;
	}

	public static ObjectNode getMember(boolean spouse) {
		ObjectNode member = ObjectUtils.getObjectNode();

		member.put("firstName", getRandomFirstname());
		member.put("lastName", getRandomLastname());

		if (spouse) {
			member.put("relationWithPrimary", ConstantUtils.relationOptions.get(1));
		} else {
			member.put("relationWithPrimary", getRandomRelation());
		}

		member.put("gender", getRandomGender());

		return member;
	}

	public static String getRandomCompany() {
		return COMPANIES.get(RandomGeneratorUtils.getIntegerWithin(0, COMPANIES.size()));
	}

	public static String getSSN() {
		String ssn = RandomGeneratorUtils.getIntegerWithin(100, 1000) + "-"
				+ RandomGeneratorUtils.getIntegerWithin(10, 100) + "-"
				+ RandomGeneratorUtils.getIntegerWithin(1000, 10000);
		return ssn;
	}

	public static int getDeductible() {
		List<Integer> ductibleOptions = Arrays.asList(0, 200, 300, 400, 500, 600, 700, 800, 900, 1000);

		return ductibleOptions.get(RandomGeneratorUtils.getIntegerWithin(0, ductibleOptions.size()));
	}

	public static int getMaxAllowance() {
		List<Integer> maxAllownceOptions = Arrays.asList(5000, 15000, 25000, 35000, 45000, 2000000);

		return maxAllownceOptions.get(RandomGeneratorUtils.getIntegerWithin(0, maxAllownceOptions.size()));

	}

	public static double getSidecarPercentage() {
		List<Double> sidecarPercentageOptions = Arrays.asList(0.5, 0.55, 0.6, 0.65, 0.7, 0.75, 0.8);

		return sidecarPercentageOptions.get(RandomGeneratorUtils.getIntegerWithin(0, sidecarPercentageOptions.size()));

	}

	public static void generateRandomArizonAddress() {
		ARIZON_ADDRESSES = new ArrayList<>();
		ObjectNode address = ObjectUtils.getObjectNode();
		address.put("street", "175 South Railroad Avenue");
		address.put("city", "Tucson");
		address.put("state", "AZ");
		address.put("zip", "85701");
		address.put("lon", -110.96461420000003);
		address.put("lat", 32.220083);

		ARIZON_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "6010 E Casper Rd");
		address.put("city", "Mesa");
		address.put("state", "AZ");
		address.put("zip", "85205");
		address.put("lon", -111.7013518);
		address.put("lat", 33.4251128);

		ARIZON_ADDRESSES.add(address);

		// 322 N 85th Pl, Mesa, AZ 85207

		address = ObjectUtils.getObjectNode();
		address.put("street", "322 N 85th Pl");
		address.put("city", "Mesa");
		address.put("state", "AZ");
		address.put("zip", "85207");
		address.put("lon", -111.6467873);
		address.put("lat", 33.4213703);

		ARIZON_ADDRESSES.add(address);

		// 7355 E Nance St, Mesa, AZ 85207

		address = ObjectUtils.getObjectNode();
		address.put("street", "7355 E Nance St");
		address.put("city", "Mesa");
		address.put("state", "AZ");
		address.put("zip", "85207");
		address.put("lon", -111.6724617);
		address.put("lat", 33.4648069);

		ARIZON_ADDRESSES.add(address);

		// 2242 E 8th St, Tucson, AZ 85719

		address = ObjectUtils.getObjectNode();
		address.put("street", "2242 E 8th St");
		address.put("city", "Tucson");
		address.put("state", "AZ");
		address.put("zip", "85719");
		address.put("lon", -110.9407675);
		address.put("lat", 32.2249927);

		ARIZON_ADDRESSES.add(address);

		// 9630 E Corte Torre Del Sol, Tucson, AZ 85748

		address = ObjectUtils.getObjectNode();
		address.put("street", "9630 E Corte Torre Del Sol");
		address.put("city", "Tucson");
		address.put("state", "AZ");
		address.put("zip", "85748");
		address.put("lon", -110.7875287);
		address.put("lat", 32.2317298);

		ARIZON_ADDRESSES.add(address);

	}

	public static void generateRandomCaliforniaAddress() {
		CALIFORNIA_ADDRESSES = new ArrayList<>();
		ObjectNode address = ObjectUtils.getObjectNode();
		address.put("street", "205 Glen Oaks Dr");
		address.put("city", "Bakersfield");
		address.put("state", "CA");
		address.put("zip", "93309");
		address.put("lon", -119.064368);
		address.put("lat", 35.352681);

		CALIFORNIA_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "2216 E South Redwood Dr");
		address.put("city", "Anaheim");
		address.put("state", "CA");
		address.put("zip", "92806");
		address.put("lon", -117.885865);
		address.put("lat", 33.844734);

		CALIFORNIA_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "4859 Premiere Ave");
		address.put("city", "Long Beach");
		address.put("state", "CA");
		address.put("zip", "90808");
		address.put("lon", -118.130971);
		address.put("lat", 33.846600);

		CALIFORNIA_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "901 Inlet Dr");
		address.put("city", "Oxnard");
		address.put("state", "CA");
		address.put("zip", "93030");
		address.put("lon", -119.190989);
		address.put("lat", 34.192374);

		CALIFORNIA_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "3635 W 225th St");
		address.put("city", "Torrance");
		address.put("state", "CA");
		address.put("zip", "90505");
		address.put("lon", -118.349399);
		address.put("lat", 33.823964);

		CALIFORNIA_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "1601 Cloverfield Blvd");
		address.put("city", "Santa Monica");
		address.put("state", "CA");
		address.put("zip", "90404");
		address.put("lon", -118.470195);
		address.put("lat", 34.010624);

		CALIFORNIA_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "1416 Marine Ave");
		address.put("city", "Manhattan Beach");
		address.put("state", "CA");
		address.put("zip", "90266");
		address.put("lon", -118.389389);
		address.put("lat", 33.894411);

		CALIFORNIA_ADDRESSES.add(address);

	}

	public static ObjectNode getRandomNonTexasAddress(String street, String zip) {
		generateRandomCaliforniaAddress();
		ObjectNode chosenAddress = null;
		while (true) {
			chosenAddress = CALIFORNIA_ADDRESSES
					.get(RandomGeneratorUtils.getIntegerWithin(0, CALIFORNIA_ADDRESSES.size() - 1));
			if (chosenAddress.get("street").asText().equals(street) == false
					&& chosenAddress.get("zip").asText().equals(zip) == false) {
				break;
			}
		}
		return chosenAddress;
	}

	public static ObjectNode getRandomNonTexasAddress() {
		generateRandomCaliforniaAddress();
		return CALIFORNIA_ADDRESSES.get(RandomGeneratorUtils.getIntegerWithin(0, CALIFORNIA_ADDRESSES.size() - 1));
	}

	public static ObjectNode getRandomArizonAddress() {
		generateRandomArizonAddress();
		return ARIZON_ADDRESSES.get(RandomGeneratorUtils.getIntegerWithin(0, ARIZON_ADDRESSES.size() - 1));
	}

	public static void generateRandomTexasAddress() {
		TEXAS_ADDRESSES = new ArrayList<>();
		ObjectNode address = ObjectUtils.getObjectNode();
		address.put("street", "500 Powell Cir");
		address.put("city", "Austin");
		address.put("state", "TX");
		address.put("zip", "78704");
		address.put("lon", -97.762114);
		address.put("lat", 30.234650);

		TEXAS_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "2000-2010 S 3rd St");
		address.put("city", "Austin");
		address.put("state", "TX");
		address.put("zip", "78704");
		address.put("lon", -97.760261);
		address.put("lat", 30.246034);

		TEXAS_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "2603 Baxter Dr");
		address.put("city", "Austin");
		address.put("state", "TX");
		address.put("zip", "78745");
		address.put("lon", -97.810232);
		address.put("lat", 30.213646);

		TEXAS_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "507 Terrell Rd");
		address.put("city", "San Antonio");
		address.put("state", "TX");
		address.put("zip", "78209");
		address.put("lon", -98.449531);
		address.put("lat", 29.471719);

		TEXAS_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "13420 Hempstead Rd");
		address.put("city", "Houston");
		address.put("state", "TX");
		address.put("zip", "77040");
		address.put("lon", -95.516854);
		address.put("lat", 29.845941);

		TEXAS_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "146 Goodhue Ave");
		address.put("city", "San Antonio");
		address.put("state", "TX");
		address.put("zip", "78218");
		address.put("lon", -98.411778);
		address.put("lat", 29.498875);

		TEXAS_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "8202 Ivan Reid Dr");
		address.put("city", "Houston");
		address.put("state", "TX");
		address.put("zip", "77040");
		address.put("lon", -95.528506);
		address.put("lat", 29.891536);

		TEXAS_ADDRESSES.add(address);
	}

	public static ObjectNode getRandomTexasAddress() {
		generateRandomTexasAddress();
		return TEXAS_ADDRESSES.get(RandomGeneratorUtils.getIntegerWithin(0, TEXAS_ADDRESSES.size() - 1));
	}

	public static ObjectNode getRandomTexasAddress(String street, String zip) {
		generateRandomTexasAddress();
		ObjectNode chosenAddress = null;
		while (true) {
			chosenAddress = TEXAS_ADDRESSES.get(RandomGeneratorUtils.getIntegerWithin(0, TEXAS_ADDRESSES.size() - 1));
			if (chosenAddress.get("street").asText().equals(street) == false
					&& chosenAddress.get("zip").asText().equals(zip) == false) {
				break;
			}
		}
		return chosenAddress;
	}

	public static void generateRandomArkansasAddress() {
		ARKANSAS_ADDRESSES = new ArrayList<>();

		ObjectNode address = ObjectUtils.getObjectNode();
		address.put("street", "100 Texarkana Avenue");
		address.put("city", "Cleveland Township");
		address.put("state", "AR");
		address.put("zip", "71822");
		address.put("timezone", "America/Chicago");
		address.put("lon", -94.151070);
		address.put("lat", 33.740105);

		ARKANSAS_ADDRESSES.add(address);

		address = ObjectUtils.getObjectNode();
		address.put("street", "250 VALLEY CLUB CIR");
		address.put("city", "LITTLE ROCK");
		address.put("state", "AR");
		address.put("zip", "72212");
		address.put("timezone", "America/Chicago");
		address.put("lon", -92.408231);
		address.put("lat", 34.785207);

		ARKANSAS_ADDRESSES.add(address);

	}

	public static ObjectNode getRandomArkansasAddress() {
		generateRandomArkansasAddress();
		return ARKANSAS_ADDRESSES.get(RandomGeneratorUtils.getIntegerWithin(0, ARKANSAS_ADDRESSES.size() - 1));
	}

	// ********************************** TOO BIG, LEAVE THESE HERE
	// ***************************

	private static final List<String> firstnames = Arrays.asList("Jacob", "Michael", "Ethan", "Joshua", "Daniel",
			"Alexander", "Anthony", "William", "Christopher", "Matthew", "Jayden", "Andrew", "Joseph", "David", "Noah",
			"Aiden", "James", "Ryan", "Logan", "John", "Nathan", "Elijah", "Christian", "Gabriel", "Benjamin",
			"Jonathan", "Tyler", "Samuel", "Nicholas", "Gavin", "Dylan", "Jackson", "Brandon", "Caleb", "Mason",
			"Angel", "Isaac", "Evan", "Jack", "Kevin", "Jose", "Isaiah", "Luke", "Landon", "Justin", "Lucas", "Zachary",
			"Jordan", "Robert", "Aaron", "Brayden", "Thomas", "Cameron", "Hunter", "Austin", "Adrian", "Connor", "Owen",
			"Aidan", "Jason", "Julian", "Wyatt", "Charles", "Luis", "Carter", "Juan", "Chase", "Diego", "Jeremiah",
			"Brody", "Xavier", "Adam", "Carlos", "Sebastian", "Liam", "Hayden", "Nathaniel", "Henry", "Jesus", "Ian",
			"Tristan", "Bryan", "Sean", "Cole", "Alex", "Eric", "Brian", "Jaden", "Carson", "Blake", "Ayden", "Cooper",
			"Dominic", "Brady", "Caden", "Josiah", "Kyle", "Colton", "Kaden", "Eli", "Miguel", "Antonio", "Parker",
			"Steven", "Alejandro", "Riley", "Richard", "Timothy", "Devin", "Jesse", "Victor", "Jake", "Joel", "Colin",
			"Kaleb", "Bryce", "Levi", "Oliver", "Oscar", "Vincent", "Ashton", "Cody", "Micah", "Preston", "Marcus",
			"Max", "Patrick", "Seth", "Jeremy", "Peyton", "Nolan", "Ivan", "Damian", "Maxwell", "Alan", "Kenneth",
			"Jonah", "Jorge", "Mark", "Giovanni", "Eduardo", "Grant", "Collin", "Gage", "Omar", "Emmanuel", "Trevor",
			"Edward", "Ricardo", "Cristian", "Nicolas", "Kayden", "George", "Jaxon", "Paul", "Braden", "Elias",
			"Andres", "Derek", "Garrett", "Tanner", "Malachi", "Conner", "Fernando", "Cesar", "Javier", "Miles",
			"Jaiden", "Alexis", "Leonardo", "Santiago", "Francisco", "Cayden", "Shane", "Edwin", "Hudson", "Travis",
			"Bryson", "Erick", "Jace", "Hector", "Josue", "Peter", "Jaylen", "Mario", "Manuel", "Abraham", "Grayson",
			"Damien", "Kaiden", "Spencer", "Stephen", "Edgar", "Wesley", "Shawn", "Trenton", "Jared", "Jeffrey",
			"Landen", "Johnathan", "Bradley", "Braxton", "Ryder", "Camden", "Roman", "Asher", "Brendan", "Maddox",
			"Sergio", "Israel", "Andy", "Lincoln", "Erik", "Donovan", "Raymond", "Avery", "Rylan", "Dalton", "Harrison",
			"Andre", "Martin", "Keegan", "Marco", "Jude", "Sawyer", "Dakota", "Leo", "Calvin", "Kai", "Drake", "Troy",
			"Zion", "Clayton", "Roberto", "Zane", "Gregory", "Tucker", "Rafael", "Kingston", "Dominick", "Ezekiel",
			"Griffin", "Devon", "Drew", "Lukas", "Johnny", "Ty", "Pedro", "Tyson", "Caiden", "Mateo", "Braylon", "Cash",
			"Aden", "Chance", "Taylor", "Marcos", "Maximus", "Ruben", "Emanuel", "Simon", "Corbin", "Brennan", "Dillon",
			"Skyler", "Myles", "Xander", "Jaxson", "Dawson", "Kameron", "Kyler", "Axel", "Colby", "Jonas", "Joaquin",
			"Payton", "Brock", "Frank", "Enrique", "Quinn", "Emilio", "Malik", "Grady", "Angelo", "Julio", "Derrick",
			"Raul", "Fabian", "Corey", "Gerardo", "Dante", "Ezra", "Armando", "Allen", "Theodore", "Gael", "Amir",
			"Zander", "Adan", "Maximilian", "Randy", "Easton", "Dustin", "Luca", "Phillip", "Julius", "Charlie",
			"Ronald", "Jakob", "Cade", "Brett", "Trent", "Silas", "Keith", "Emiliano", "Trey", "Jalen", "Darius",
			"Lane", "Jerry", "Jaime", "Scott", "Graham", "Weston", "Braydon", "Anderson", "Rodrigo", "Pablo", "Saul",
			"Danny", "Donald", "Elliot", "Brayan", "Dallas", "Lorenzo", "Casey", "Mitchell", "Alberto", "Tristen",
			"Rowan", "Jayson", "Gustavo", "Aaden", "Amari", "Dean", "Braeden", "Declan", "Chris", "Ismael", "Dane",
			"Louis", "Arturo", "Brenden", "Felix", "Jimmy", "Cohen", "Tony", "Holden", "Reid", "Abel", "Bennett",
			"Zackary", "Arthur", "Nehemiah", "Ricky", "Esteban", "Cruz", "Finn", "Mauricio", "Dennis", "Keaton",
			"Albert", "Marvin", "Mathew", "Larry", "Moises", "Issac", "Philip", "Quentin", "Curtis", "Greyson",
			"Jameson", "Everett", "Jayce", "Darren", "Elliott", "Uriel", "Alfredo", "Hugo", "Alec", "Jamari",
			"Marshall", "Walter", "Judah", "Jay", "Lance", "Beau", "Ali", "Landyn", "Yahir", "Phoenix", "Nickolas",
			"Kobe", "Bryant", "Maurice", "Russell", "Leland", "Colten", "Reed", "Davis", "Joe", "Ernesto", "Desmond",
			"Kade", "Reece", "Morgan", "Ramon", "Rocco", "Orlando", "Ryker", "Brodie", "Paxton", "Jacoby", "Douglas",
			"Kristopher", "Gary", "Lawrence", "Izaiah", "Solomon", "Nikolas", "Mekhi", "Justice", "Tate", "Jaydon",
			"Salvador", "Shaun", "Alvin", "Eddie", "Kane", "Davion", "Zachariah", "Dorian", "Titus", "Kellen", "Camron",
			"Isiah", "Javon", "Nasir", "Milo", "Johan", "Byron", "Jasper", "Jonathon", "Chad", "Marc", "Kelvin",
			"Chandler", "Sam", "Cory", "Deandre", "River", "Reese", "Roger", "Quinton", "Talon", "Romeo", "Franklin",
			"Noel", "Alijah", "Guillermo", "Gunner", "Damon", "Jadon", "Emerson", "Micheal", "Bruce", "Terry", "Kolton",
			"Melvin", "Beckett", "Porter", "August", "Brycen", "Dayton", "Jamarion", "Leonel", "Karson", "Zayden",
			"Keagan", "Carl", "Khalil", "Cristopher", "Nelson", "Braiden", "Moses", "Isaias", "Roy", "Triston",
			"Walker", "Kale", "Jermaine", "Leon", "Rodney", "Kristian", "Mohamed", "Ronan", "Pierce", "Trace", "Warren",
			"Jeffery", "Maverick", "Cyrus", "Quincy", "Nathanael", "Skylar", "Tommy", "Conor", "Noe", "Ezequiel",
			"Demetrius", "Jaylin", "Kendrick", "Frederick", "Terrance", "Bobby", "Jamison", "Jon", "Rohan", "Jett",
			"Kieran", "Tobias", "Ari", "Colt", "Gideon", "Felipe", "Kenny", "Wilson", "Orion", "Kamari", "Gunnar",
			"Jessie", "Alonzo", "Gianni", "Omari", "Waylon", "Malcolm", "Emmett", "Abram", "Julien", "London", "Tomas",
			"Allan", "Terrell", "Matteo", "Tristin", "Jairo", "Reginald", "Brent", "Ahmad", "Yandel", "Rene", "Willie",
			"Boston", "Billy", "Marlon", "Trevon", "Aydan", "Jamal", "Aldo", "Ariel", "Cason", "Braylen", "Javion",
			"Joey", "Rogelio", "Ahmed", "Dominik", "Brendon", "Toby", "Kody", "Marquis", "Ulises", "Armani", "Adriel",
			"Alfonso", "Branden", "Will", "Craig", "Ibrahim", "Osvaldo", "Wade", "Harley", "Steve", "Davin", "Deshawn",
			"Kason", "Damion", "Jaylon", "Jefferson", "Aron", "Brooks", "Darian", "Gerald", "Rolando", "Terrence",
			"Enzo", "Kian", "Ryland", "Barrett", "Jaeden", "Ben", "Bradyn", "Giovani", "Blaine", "Madden", "Jerome",
			"Muhammad", "Ronnie", "Layne", "Kolby", "Leonard", "Vicente", "Cale", "Alessandro", "Zachery", "Gavyn",
			"Aydin", "Xzavier", "Malakai", "Raphael", "Cannon", "Rudy", "Asa", "Darrell", "Giancarlo", "Elisha",
			"Junior", "Zackery", "Alvaro", "Lewis", "Valentin", "Deacon", "Jase", "Harry", "Kendall", "Rashad",
			"Finnegan", "Mohammed", "Ramiro", "Cedric", "Brennen", "Santino", "Stanley", "Tyrone", "Chace", "Francis",
			"Johnathon", "Teagan", "Zechariah", "Alonso", "Kaeden", "Kamden", "Gilberto", "Ray", "Karter", "Luciano",
			"Nico", "Kole", "Aryan", "Draven", "Jamie", "Misael", "Lee", "Alexzander", "Camren", "Giovanny", "Amare",
			"Rhett", "Rhys", "Rodolfo", "Nash", "Markus", "Deven", "Mohammad", "Moshe", "Quintin", "Dwayne", "Memphis",
			"Atticus", "Davian", "Eugene", "Jax", "Antoine", "Wayne", "Randall", "Semaj", "Uriah", "Clark", "Aidyn",
			"Jorden", "Maxim", "Aditya", "Lawson", "Messiah", "Korbin", "Sullivan", "Freddy", "Demarcus", "Neil",
			"Brice", "King", "Davon", "Elvis", "Ace", "Dexter", "Heath", "Duncan", "Jamar", "Sincere", "Irvin",
			"Remington", "Kadin", "Soren", "Tyree", "Damarion", "Talan", "Adrien", "Gilbert", "Keenan", "Darnell",
			"Adolfo", "Tristian", "Derick", "Isai", "Rylee", "Gauge", "Harold", "Kareem", "Deangelo", "Agustin",
			"Coleman", "Zavier", "Lamar", "Emery", "Jaydin", "Devan", "Jordyn", "Mathias", "Prince", "Sage", "Seamus",
			"Jasiah", "Efrain", "Darryl", "Arjun", "Mike", "Roland", "Conrad", "Kamron", "Hamza", "Santos", "Frankie",
			"Dominique", "Marley", "Vance", "Dax", "Jamir", "Kylan", "Todd", "Maximo", "Jabari", "Matthias", "Haiden",
			"Luka", "Marcelo", "Keon", "Layton", "Tyrell", "Kash", "Raiden", "Cullen", "Donte", "Jovani", "Cordell",
			"Kasen", "Rory", "Alfred", "Darwin", "Ernest", "Bailey", "Gaige", "Hassan", "Jamarcus", "Killian",
			"Augustus", "Trevin", "Zain", "Ellis", "Rex", "Yusuf", "Bruno", "Jaidyn", "Justus", "Ronin", "Humberto",
			"Jaquan", "Josh", "Kasey", "Winston", "Dashawn", "Lucian", "Matias", "Sidney", "Ignacio", "Nigel", "Van",
			"Elian", "Finley", "Jaron", "Addison", "Aedan", "Braedon", "Jadyn", "Konner", "Zayne", "Franco", "Niko",
			"Savion", "Cristofer", "Deon", "Krish", "Anton", "Brogan", "Cael", "Coby", "Kymani", "Marcel", "Yair",
			"Dale", "Bo", "Jordon", "Samir", "Darien", "Zaire", "Ross", "Vaughn", "Devyn", "Kenyon", "Clay", "Dario",
			"Ishaan", "Jair", "Kael", "Adonis", "Jovanny", "Clinton", "Rey", "Chaim", "German", "Harper", "Nathen",
			"Rigoberto", "Sonny", "Glenn", "Octavio", "Blaze", "Keshawn", "Ralph", "Ean", "Nikhil", "Rayan", "Sterling",
			"Branson", "Jadiel", "Dillan", "Jeramiah", "Koen", "Konnor", "Antwan", "Houston", "Tyrese", "Dereon",
			"Leonidas", "Zack", "Fisher", "Jaydan", "Quinten", "Nick", "Urijah", "Darion", "Jovan", "Salvatore",
			"Beckham", "Jarrett", "Antony", "Eden", "Makai", "Zaiden", "Broderick", "Camryn", "Malaki", "Nikolai",
			"Howard", "Immanuel", "Demarion", "Valentino", "Jovanni", "Ayaan", "Ethen", "Leandro", "Royce", "Yael",
			"Yosef", "Jean", "Marquise", "Alden", "Leroy", "Gaven", "Jovany", "Tyshawn", "Aarav", "Kadyn", "Milton",
			"Zaid", "Kelton", "Tripp", "Kamren", "Slade", "Hezekiah", "Jakobe", "Nathanial", "Rishi", "Shamar",
			"Geovanni", "Pranav", "Roderick", "Bentley", "Clarence", "Lyric", "Bernard", "Carmelo", "Denzel",
			"Maximillian", "Reynaldo", "Cassius", "Gordon", "Reuben", "Samson", "Yadiel", "Jayvon", "Reilly", "Sheldon",
			"Abdullah", "Jagger", "Thaddeus", "Case", "Kyson", "Lamont", "Chaz", "Makhi", "Jan", "Marques", "Oswaldo",
			"Donavan", "Keyon", "Kyan", "Simeon", "Trystan", "Andreas", "Dangelo", "Landin", "Reagan", "Turner",
			"Arnav", "Brenton", "Callum", "Jayvion", "Bridger", "Sammy", "Deegan", "Jaylan", "Lennon", "Odin", "Abdiel",
			"Jerimiah", "Eliezer", "Bronson", "Cornelius", "Pierre", "Cortez", "Baron", "Carlo", "Carsen", "Fletcher",
			"Izayah", "Kolten", "Damari", "Hugh", "Jensen", "Yurem", "Emma", "Isabella", "Emily", "Madison", "Ava",
			"Olivia", "Sophia", "Abigail", "Elizabeth", "Chloe", "Samantha", "Addison", "Natalie", "Mia", "Alexis",
			"Alyssa", "Hannah", "Ashley", "Ella", "Sarah", "Grace", "Taylor", "Brianna", "Lily", "Hailey", "Anna",
			"Victoria", "Kayla", "Lillian", "Lauren", "Kaylee", "Allison", "Savannah", "Nevaeh", "Gabriella", "Sofia",
			"Makayla", "Avery", "Riley", "Julia", "Leah", "Aubrey", "Jasmine", "Audrey", "Katherine", "Morgan",
			"Brooklyn", "Destiny", "Sydney", "Alexa", "Kylie", "Brooke", "Kaitlyn", "Evelyn", "Layla", "Madeline",
			"Kimberly", "Zoe", "Jessica", "Peyton", "Alexandra", "Claire", "Madelyn", "Maria", "Mackenzie", "Arianna",
			"Jocelyn", "Amelia", "Angelina", "Trinity", "Andrea", "Maya", "Valeria", "Sophie", "Rachel", "Vanessa",
			"Aaliyah", "Mariah", "Gabrielle", "Katelyn", "Ariana", "Bailey", "Camila", "Jennifer", "Melanie", "Gianna",
			"Charlotte", "Paige", "Autumn", "Payton", "Faith", "Sara", "Isabelle", "Caroline", "Genesis", "Isabel",
			"Mary", "Zoey", "Gracie", "Megan", "Haley", "Mya", "Michelle", "Molly", "Stephanie", "Nicole", "Jenna",
			"Natalia", "Sadie", "Jada", "Serenity", "Lucy", "Ruby", "Eva", "Kennedy", "Rylee", "Jayla", "Naomi",
			"Rebecca", "Lydia", "Daniela", "Bella", "Keira", "Adriana", "Lilly", "Hayden", "Miley", "Katie", "Jade",
			"Jordan", "Gabriela", "Amy", "Angela", "Melissa", "Valerie", "Giselle", "Diana", "Amanda", "Kate", "Laila",
			"Reagan", "Jordyn", "Kylee", "Danielle", "Briana", "Marley", "Leslie", "Kendall", "Catherine", "Liliana",
			"Mckenzie", "Jacqueline", "Ashlyn", "Reese", "Marissa", "London", "Juliana", "Shelby", "Cheyenne", "Angel",
			"Daisy", "Makenzie", "Miranda", "Erin", "Amber", "Alana", "Ellie", "Breanna", "Ana", "Mikayla", "Summer",
			"Piper", "Adrianna", "Jillian", "Sierra", "Jayden", "Sienna", "Alicia", "Lila", "Margaret", "Alivia",
			"Brooklynn", "Karen", "Violet", "Sabrina", "Stella", "Aniyah", "Annabelle", "Alexandria", "Kathryn",
			"Skylar", "Aliyah", "Delilah", "Julianna", "Kelsey", "Khloe", "Carly", "Amaya", "Mariana", "Christina",
			"Alondra", "Tessa", "Eliana", "Bianca", "Jazmin", "Clara", "Vivian", "Josephine", "Delaney", "Scarlett",
			"Elena", "Cadence", "Alexia", "Maggie", "Laura", "Nora", "Ariel", "Elise", "Nadia", "Mckenna", "Chelsea",
			"Lyla", "Alaina", "Jasmin", "Hope", "Leila", "Caitlyn", "Cassidy", "Makenna", "Allie", "Izabella", "Eden",
			"Callie", "Haylee", "Caitlin", "Kendra", "Karina", "Kyra", "Kayleigh", "Addyson", "Kiara", "Jazmine",
			"Karla", "Camryn", "Alina", "Lola", "Kyla", "Kelly", "Fatima", "Tiffany", "Kira", "Crystal", "Mallory",
			"Esmeralda", "Alejandra", "Eleanor", "Angelica", "Jayda", "Abby", "Kara", "Veronica", "Carmen", "Jamie",
			"Ryleigh", "Valentina", "Allyson", "Dakota", "Kamryn", "Courtney", "Cecilia", "Madeleine", "Aniya",
			"Alison", "Esther", "Heaven", "Aubree", "Lindsey", "Leilani", "Nina", "Melody", "Macy", "Ashlynn", "Joanna",
			"Cassandra", "Alayna", "Kaydence", "Madilyn", "Aurora", "Heidi", "Emerson", "Kimora", "Madalyn", "Erica",
			"Josie", "Katelynn", "Guadalupe", "Harper", "Ivy", "Lexi", "Camille", "Savanna", "Dulce", "Daniella",
			"Lucia", "Emely", "Joselyn", "Kiley", "Kailey", "Miriam", "Cynthia", "Rihanna", "Georgia", "Rylie",
			"Harmony", "Kiera", "Kyleigh", "Monica", "Bethany", "Kaylie", "Cameron", "Teagan", "Cora", "Brynn", "Ciara",
			"Genevieve", "Alice", "Maddison", "Eliza", "Tatiana", "Jaelyn", "Erika", "Ximena", "April", "Marely",
			"Julie", "Danica", "Presley", "Brielle", "Julissa", "Angie", "Iris", "Brenda", "Hazel", "Rose", "Malia",
			"Shayla", "Fiona", "Phoebe", "Nayeli", "Paola", "Kaelyn", "Selena", "Audrina", "Rebekah", "Carolina",
			"Janiyah", "Michaela", "Penelope", "Janiya", "Anastasia", "Adeline", "Ruth", "Sasha", "Denise", "Holly",
			"Madisyn", "Hanna", "Tatum", "Marlee", "Nataly", "Helen", "Janelle", "Lizbeth", "Serena", "Anya", "Jaslene",
			"Kaylin", "Jazlyn", "Nancy", "Lindsay", "Desiree", "Hayley", "Itzel", "Imani", "Madelynn", "Asia",
			"Kadence", "Madyson", "Talia", "Jane", "Kayden", "Annie", "Amari", "Bridget", "Raegan", "Jadyn", "Celeste",
			"Jimena", "Luna", "Yasmin", "Emilia", "Annika", "Estrella", "Sarai", "Lacey", "Ayla", "Alessandra",
			"Willow", "Nyla", "Dayana", "Lilah", "Lilliana", "Natasha", "Hadley", "Harley", "Priscilla", "Claudia",
			"Allisson", "Baylee", "Brenna", "Brittany", "Skyler", "Fernanda", "Danna", "Melany", "Cali", "Lia", "Macie",
			"Lyric", "Logan", "Gloria", "Lana", "Mylee", "Cindy", "Lilian", "Amira", "Anahi", "Alissa", "Anaya", "Lena",
			"Ainsley", "Sandra", "Noelle", "Marisol", "Meredith", "Kailyn", "Lesly", "Johanna", "Diamond", "Evangeline",
			"Juliet", "Kathleen", "Meghan", "Paisley", "Athena", "Hailee", "Rosa", "Wendy", "Emilee", "Sage", "Alanna",
			"Elaina", "Cara", "Nia", "Paris", "Casey", "Dana", "Emery", "Rowan", "Aubrie", "Kaitlin", "Jaden", "Kenzie",
			"Kiana", "Viviana", "Norah", "Lauryn", "Perla", "Amiyah", "Alyson", "Rachael", "Shannon", "Aileen",
			"Miracle", "Lillie", "Danika", "Heather", "Kassidy", "Taryn", "Tori", "Francesca", "Kristen", "Amya",
			"Elle", "Kristina", "Cheyanne", "Haylie", "Patricia", "Anne", "Samara", "Skye", "Kali", "America", "Lexie",
			"Parker", "Halle", "Londyn", "Abbigail", "Linda", "Hallie", "Saniya", "Bryanna", "Bailee", "Jaylynn",
			"Mckayla", "Quinn", "Jaelynn", "Jaida", "Caylee", "Jaiden", "Melina", "Abril", "Sidney", "Kassandra",
			"Elisabeth", "Adalyn", "Kaylynn", "Mercedes", "Yesenia", "Elliana", "Brylee", "Dylan", "Isabela", "Ryan",
			"Ashlee", "Daphne", "Kenya", "Marina", "Christine", "Mikaela", "Kaitlynn", "Justice", "Saniyah", "Jaliyah",
			"Ingrid", "Marie", "Natalee", "Joy", "Juliette", "Simone", "Adelaide", "Krystal", "Kennedi", "Mila",
			"Tamia", "Addisyn", "Aylin", "Dayanara", "Sylvia", "Clarissa", "Maritza", "Virginia", "Braelyn", "Jolie",
			"Jaidyn", "Kinsley", "Kirsten", "Laney", "Marilyn", "Whitney", "Janessa", "Raquel", "Anika", "Kamila",
			"Aria", "Rubi", "Adelyn", "Amara", "Ayanna", "Teresa", "Zariah", "Kaleigh", "Amani", "Carla", "Yareli",
			"Gwendolyn", "Paulina", "Nathalie", "Annabella", "Jaylin", "Tabitha", "Deanna", "Madalynn", "Journey",
			"Aiyana", "Skyla", "Yaretzi", "Ada", "Liana", "Karlee", "Jenny", "Myla", "Cristina", "Myah", "Lisa",
			"Tania", "Isis", "Jayleen", "Jordin", "Arely", "Azul", "Helena", "Aryanna", "Jaqueline", "Lucille",
			"Destinee", "Martha", "Zoie", "Arielle", "Liberty", "Marlene", "Elisa", "Isla", "Noemi", "Raven", "Jessie",
			"Aleah", "Kailee", "Kaliyah", "Lilyana", "Haven", "Tara", "Giana", "Camilla", "Maliyah", "Irene", "Carley",
			"Maeve", "Lea", "Macey", "Sharon", "Alisha", "Marisa", "Jaylene", "Kaya", "Scarlet", "Siena", "Adyson",
			"Maia", "Shiloh", "Tiana", "Jaycee", "Gisselle", "Yazmin", "Eve", "Shyanne", "Arabella", "Sherlyn",
			"Sariah", "Amiya", "Kiersten", "Madilynn", "Shania", "Aleena", "Finley", "Kinley", "Kaia", "Aliya",
			"Taliyah", "Pamela", "Yoselin", "Ellen", "Carlie", "Monserrat", "Jakayla", "Reyna", "Yaritza", "Carolyn",
			"Clare", "Lorelei", "Paula", "Zaria", "Gracelyn", "Kasey", "Regan", "Alena", "Angelique", "Regina",
			"Britney", "Emilie", "Mariam", "Jaylee", "Julianne", "Greta", "Elyse", "Lainey", "Kallie", "Felicity",
			"Zion", "Aspen", "Carlee", "Annalise", "Iliana", "Larissa", "Akira", "Sonia", "Catalina", "Phoenix",
			"Joslyn", "Anabelle", "Mollie", "Susan", "Judith", "Destiney", "Hillary", "Janet", "Katrina", "Mareli",
			"Ansley", "Kaylyn", "Alexus", "Gia", "Maci", "Elsa", "Stacy", "Kaylen", "Carissa", "Haleigh", "Lorena",
			"Jazlynn", "Milagros", "Luz", "Leanna", "Renee", "Shaniya", "Charlie", "Abbie", "Cailyn", "Cherish",
			"Elsie", "Jazmyn", "Elaine", "Emmalee", "Luciana", "Dahlia", "Jamya", "Belinda", "Mariyah", "Chaya",
			"Dayami", "Rhianna", "Yadira", "Aryana", "Rosemary", "Armani", "Cecelia", "Celia", "Barbara", "Cristal",
			"Eileen", "Rayna", "Campbell", "Amina", "Aisha", "Amirah", "Ally", "Araceli", "Averie", "Mayra", "Sanaa",
			"Patience", "Leyla", "Selah", "Zara", "Chanel", "Kaiya", "Keyla", "Miah", "Aimee", "Giovanna", "Amelie",
			"Kelsie", "Alisson", "Angeline", "Dominique", "Adrienne", "Brisa", "Cierra", "Paloma", "Isabell",
			"Precious", "Alma", "Charity", "Jacquelyn", "Janae", "Frances", "Shyla", "Janiah", "Kierra", "Karlie",
			"Annabel", "Jacey", "Karissa", "Jaylah", "Xiomara", "Edith", "Marianna", "Damaris", "Deborah", "Jaylyn",
			"Evelin", "Mara", "Olive", "Ayana", "India", "Kendal", "Kayley", "Tamara", "Briley", "Charlee", "Nylah",
			"Abbey", "Moriah", "Saige", "Savanah", "Giada", "Hana", "Lizeth", "Matilda", "Ann", "Jazlene", "Gillian",
			"Beatrice", "Ireland", "Karly", "Mylie", "Yasmine", "Ashly", "Kenna", "Maleah", "Corinne", "Keely", "Tanya",
			"Tianna", "Adalynn", "Ryann", "Salma", "Areli", "Karma", "Shyann", "Kaley", "Theresa", "Evie", "Gina",
			"Roselyn", "Kaila", "Jaylen", "Natalya", "Meadow", "Rayne", "Aliza", "Yuliana", "June", "Lilianna",
			"Nathaly", "Ali", "Alisa", "Aracely", "Belen", "Tess", "Jocelynn", "Litzy", "Makena", "Abagail", "Giuliana",
			"Joyce", "Libby", "Lillianna", "Thalia", "Tia", "Sarahi", "Zaniyah", "Kristin", "Lorelai", "Mattie",
			"Taniya", "Jaslyn", "Gemma", "Valery", "Lailah", "Mckinley", "Micah", "Deja", "Frida", "Brynlee", "Jewel",
			"Krista", "Mira", "Yamilet", "Adison", "Carina", "Karli", "Magdalena", "Stephany", "Charlize", "Raelynn",
			"Aliana", "Cassie", "Mina", "Karley", "Shirley", "Marlie", "Alani", "Taniyah", "Cloe", "Sanai", "Lina",
			"Nola", "Anabella", "Dalia", "Raina", "Mariela", "Ariella", "Bria", "Kamari", "Monique", "Ashleigh",
			"Reina", "Alia", "Ashanti", "Lara", "Lilia", "Justine", "Leia", "Maribel", "Abigayle", "Tiara", "Alannah",
			"Princess", "Sydnee", "Kamora", "Paityn", "Payten", "Naima", "Gretchen", "Heidy", "Nyasia", "Livia",
			"Marin", "Shaylee", "Maryjane", "Laci", "Nathalia", "Azaria", "Anabel", "Chasity", "Emmy", "Izabelle",
			"Denisse", "Emelia", "Mireya", "Shea", "Amiah", "Dixie", "Maren", "Averi", "Esperanza", "Micaela", "Selina",
			"Alyvia", "Chana", "Avah", "Donna", "Kaylah", "Ashtyn", "Karsyn", "Makaila", "Shayna", "Essence", "Leticia",
			"Miya", "Rory", "Desirae", "Kianna", "Laurel", "Neveah", "Amaris", "Hadassah", "Dania", "Hailie", "Jamiya",
			"Kathy", "Laylah", "Riya", "Diya", "Carleigh", "Iyana", "Kenley", "Sloane", "Elianna");

	private static final List<String> lastnames = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones",
			"Miller", "Davis", "Garcia", "Rodriguez", "Wilson", "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez",
			"Moore", "Martin", "Jackson", "Thompson", "White", "Lopez", "Lee", "Gonzalez", "Harris", "Clark", "Lewis",
			"Robinson", "Walker", "Perez", "Hall", "Young", "Allen", "Sanchez", "Wright", "King", "Scott", "Green",
			"Baker", "Adams", "Nelson", "Hill", "Ramirez", "Campbell", "Mitchell", "Roberts", "Carter", "Phillips",
			"Evans", "Turner", "Torres", "Parker", "Collins", "Edwards", "Stewart", "Flores", "Morris", "Nguyen",
			"Murphy", "Rivera", "Cook", "Rogers", "Morgan", "Peterson", "Cooper", "Reed", "Bailey", "Bell", "Gomez",
			"Kelly", "Howard", "Ward", "Cox", "Diaz", "Richardson", "Wood", "Watson", "Brooks", "Bennett", "Gray",
			"James", "Reyes", "Cruz", "Hughes", "Price", "Myers", "Long", "Foster", "Sanders", "Ross", "Morales",
			"Powell", "Sullivan", "Russell", "Ortiz", "Jenkins", "Gutierrez", "Perry", "Butler", "Barnes", "Fisher",
			"Henderson", "Coleman", "Simmons", "Patterson", "Jordan", "Reynolds", "Hamilton", "Graham", "Kim",
			"Gonzales", "Alexander", "Ramos", "Wallace", "Griffin", "West", "Cole", "Hayes", "Chavez", "Gibson",
			"Bryant", "Ellis", "Stevens", "Murray", "Ford", "Marshall", "Owens", "Mcdonald", "Harrison", "Ruiz",
			"Kennedy", "Wells", "Alvarez", "Woods", "Mendoza", "Castillo", "Olson", "Webb", "Washington", "Tucker",
			"Freeman", "Burns", "Henry", "Vasquez", "Snyder", "Simpson", "Crawford", "Jimenez", "Porter", "Mason",
			"Shaw", "Gordon", "Wagner", "Hunter", "Romero", "Hicks", "Dixon", "Hunt", "Palmer", "Robertson", "Black",
			"Holmes", "Stone", "Meyer", "Boyd", "Mills", "Warren", "Fox", "Rose", "Rice", "Moreno", "Schmidt", "Patel",
			"Ferguson", "Nichols", "Herrera", "Medina", "Ryan", "Fernandez", "Weaver", "Daniels", "Stephens", "Gardner",
			"Payne", "Kelley", "Dunn", "Pierce", "Arnold", "Tran", "Spencer", "Peters", "Hawkins", "Grant", "Hansen",
			"Castro", "Hoffman", "Hart", "Elliott", "Cunningham", "Knight", "Bradley", "Carroll", "Hudson", "Duncan",
			"Armstrong", "Berry", "Andrews", "Johnston", "Ray", "Lane", "Riley", "Carpenter", "Perkins", "Aguilar",
			"Silva", "Richards", "Willis", "Matthews", "Chapman", "Lawrence", "Garza", "Vargas", "Watkins", "Wheeler",
			"Larson", "Carlson", "Harper", "George", "Greene", "Burke", "Guzman", "Morrison", "Munoz", "Jacobs",
			"Obrien", "Lawson", "Franklin", "Lynch", "Bishop", "Carr", "Salazar", "Austin", "Mendez", "Gilbert",
			"Jensen", "Williamson", "Montgomery", "Harvey", "Oliver", "Howell", "Dean", "Hanson", "Weber", "Garrett",
			"Sims", "Burton", "Fuller", "Soto", "Mccoy", "Welch", "Chen", "Schultz", "Walters", "Reid", "Fields",
			"Walsh", "Little", "Fowler", "Bowman", "Davidson", "May", "Day", "Schneider", "Newman", "Brewer", "Lucas",
			"Holland", "Wong", "Banks", "Santos", "Curtis", "Pearson", "Delgado", "Valdez", "Pena", "Rios", "Douglas",
			"Sandoval", "Barrett", "Hopkins", "Keller", "Guerrero", "Stanley", "Bates", "Alvarado", "Beck", "Ortega",
			"Wade", "Estrada", "Contreras", "Barnett", "Caldwell", "Santiago", "Lambert", "Powers", "Chambers", "Nunez",
			"Craig", "Leonard", "Lowe", "Rhodes", "Byrd", "Gregory", "Shelton", "Frazier", "Becker", "Maldonado",
			"Fleming", "Vega", "Sutton", "Cohen", "Jennings", "Parks", "Mcdaniel", "Watts", "Barker", "Norris",
			"Vaughn", "Vazquez", "Holt", "Schwartz", "Steele", "Benson", "Neal", "Dominguez", "Horton", "Terry",
			"Wolfe", "Hale", "Lyons", "Graves", "Haynes", "Miles", "Park", "Warner", "Padilla", "Bush", "Thornton",
			"Mccarthy", "Mann", "Zimmerman", "Erickson", "Fletcher", "Mckinney", "Page", "Dawson", "Joseph", "Marquez",
			"Reeves", "Klein", "Espinoza", "Baldwin", "Moran", "Love", "Robbins", "Higgins", "Ball", "Cortez", "Le",
			"Griffith", "Bowen", "Sharp", "Cummings", "Ramsey", "Hardy", "Swanson", "Barber", "Acosta", "Luna",
			"Chandler", "Blair", "Daniel", "Cross", "Simon", "Dennis", "Oconnor", "Quinn", "Gross", "Navarro", "Moss",
			"Fitzgerald", "Doyle", "Mclaughlin", "Rojas", "Rodgers", "Stevenson", "Singh", "Yang", "Figueroa", "Harmon",
			"Newton", "Paul", "Manning", "Garner", "Mcgee", "Reese", "Francis", "Burgess", "Adkins", "Goodman", "Curry",
			"Brady", "Christensen", "Potter", "Walton", "Goodwin", "Mullins", "Molina", "Webster", "Fischer", "Campos",
			"Avila", "Sherman", "Todd", "Chang", "Blake", "Malone", "Wolf", "Hodges", "Juarez", "Gill", "Farmer",
			"Hines", "Gallagher", "Duran", "Hubbard", "Cannon", "Miranda", "Wang", "Saunders", "Tate", "Mack",
			"Hammond", "Carrillo", "Townsend", "Wise", "Ingram", "Barton", "Mejia", "Ayala", "Schroeder", "Hampton",
			"Rowe", "Parsons", "Frank", "Waters", "Strickland", "Osborne", "Maxwell", "Chan", "Deleon", "Norman",
			"Harrington", "Casey", "Patton", "Logan", "Bowers", "Mueller", "Glover", "Floyd", "Hartman", "Buchanan",
			"Cobb", "French", "Kramer", "Mccormick", "Clarke", "Tyler", "Gibbs", "Moody", "Conner", "Sparks", "Mcguire",
			"Leon", "Bauer", "Norton", "Pope", "Flynn", "Hogan", "Robles", "Salinas", "Yates", "Lindsey", "Lloyd",
			"Marsh", "Mcbride", "Owen", "Solis", "Pham", "Lang", "Pratt", "Lara", "Brock", "Ballard", "Trujillo",
			"Shaffer", "Drake", "Roman", "Aguirre", "Morton", "Stokes", "Lamb", "Pacheco", "Patrick", "Cochran",
			"Shepherd", "Cain", "Burnett", "Hess", "Li", "Cervantes", "Olsen", "Briggs", "Ochoa", "Cabrera",
			"Velasquez", "Montoya", "Roth", "Meyers", "Cardenas", "Fuentes", "Weiss", "Hoover", "Wilkins", "Nicholson",
			"Underwood", "Short", "Carson", "Morrow", "Colon", "Holloway", "Summers", "Bryan", "Petersen", "Mckenzie",
			"Serrano", "Wilcox", "Carey", "Clayton", "Poole", "Calderon", "Gallegos", "Greer", "Rivas", "Guerra",
			"Decker", "Collier", "Wall", "Whitaker", "Bass", "Flowers", "Davenport", "Conley", "Houston", "Huff",
			"Copeland", "Hood", "Monroe", "Massey", "Roberson", "Combs", "Franco", "Larsen", "Pittman", "Randall",
			"Skinner", "Wilkinson", "Kirby", "Cameron", "Bridges", "Anthony", "Richard", "Kirk", "Bruce", "Singleton",
			"Mathis", "Bradford", "Boone", "Abbott", "Charles", "Allison", "Sweeney", "Atkinson", "Horn", "Jefferson",
			"Rosales", "York", "Christian", "Phelps", "Farrell", "Castaneda", "Nash", "Dickerson", "Bond", "Wyatt",
			"Foley", "Chase", "Gates", "Vincent", "Mathews", "Hodge", "Garrison", "Trevino", "Villarreal", "Heath",
			"Dalton", "Valencia", "Callahan", "Hensley", "Atkins", "Huffman", "Roy", "Boyer", "Shields", "Lin",
			"Hancock", "Grimes", "Glenn", "Cline", "Delacruz", "Camacho", "Dillon", "Parrish", "Oneill", "Melton",
			"Booth", "Kane", "Berg", "Harrell", "Pitts", "Savage", "Wiggins", "Brennan", "Salas", "Marks", "Russo",
			"Sawyer", "Baxter", "Golden", "Hutchinson", "Liu", "Walter", "Mcdowell", "Wiley", "Rich", "Humphrey",
			"Johns", "Koch", "Suarez", "Hobbs", "Beard", "Gilmore", "Ibarra", "Keith", "Macias", "Khan", "Andrade",
			"Ware", "Stephenson", "Henson", "Wilkerson", "Dyer", "Mcclure", "Blackwell", "Mercado", "Tanner", "Eaton",
			"Clay", "Barron", "Beasley", "Oneal", "Preston", "Small", "Wu", "Zamora", "Macdonald", "Vance", "Snow",
			"Mcclain", "Stafford", "Orozco", "Barry", "English", "Shannon", "Kline", "Jacobson", "Woodard", "Huang",
			"Kemp", "Mosley", "Prince", "Merritt", "Hurst", "Villanueva", "Roach", "Nolan", "Lam", "Yoder",
			"Mccullough", "Lester", "Santana", "Valenzuela", "Winters", "Barrera", "Leach", "Orr", "Berger", "Mckee",
			"Strong", "Conway", "Stein", "Whitehead", "Bullock", "Escobar", "Knox", "Meadows", "Solomon", "Velez",
			"Odonnell", "Kerr", "Stout", "Blankenship", "Browning", "Kent", "Lozano", "Bartlett", "Pruitt", "Buck",
			"Barr", "Gaines", "Durham", "Gentry", "Mcintyre", "Sloan", "Melendez", "Rocha", "Herman", "Sexton", "Moon",
			"Hendricks", "Rangel", "Stark", "Lowery", "Hardin", "Hull", "Sellers", "Ellison", "Calhoun", "Gillespie",
			"Mora", "Knapp", "Mccall", "Morse", "Dorsey", "Weeks", "Nielsen", "Livingston", "Leblanc", "Mclean",
			"Bradshaw", "Glass", "Middleton", "Buckley", "Schaefer", "Frost", "Howe", "House", "Mcintosh", "Ho",
			"Pennington", "Reilly", "Hebert", "Mcfarland", "Hickman", "Noble", "Spears", "Conrad", "Arias", "Galvan",
			"Velazquez", "Huynh", "Frederick", "Randolph", "Cantu", "Fitzpatrick", "Mahoney", "Peck", "Villa",
			"Michael", "Donovan", "Mcconnell", "Walls", "Boyle", "Mayer", "Zuniga", "Giles", "Pineda", "Pace", "Hurley",
			"Mays", "Mcmillan", "Crosby", "Ayers", "Case", "Bentley", "Shepard", "Everett", "Pugh", "David", "Mcmahon",
			"Dunlap", "Bender", "Hahn", "Harding", "Acevedo", "Raymond", "Blackburn", "Duffy", "Landry", "Dougherty",
			"Bautista", "Shah", "Potts", "Arroyo", "Valentine", "Meza", "Gould", "Vaughan", "Fry", "Rush", "Avery",
			"Herring", "Dodson", "Clements", "Sampson", "Tapia", "Bean", "Lynn", "Crane", "Farley", "Cisneros",
			"Benton", "Ashley", "Mckay", "Finley", "Best", "Blevins", "Friedman", "Moses", "Sosa", "Blanchard", "Huber",
			"Frye", "Krueger", "Bernard", "Rosario", "Rubio", "Mullen", "Benjamin", "Haley", "Chung", "Moyer", "Choi",
			"Horne", "Yu", "Woodward", "Ali", "Nixon", "Hayden", "Rivers", "Estes", "Mccarty", "Richmond", "Stuart",
			"Maynard", "Brandt", "Oconnell", "Hanna", "Sanford", "Sheppard", "Church", "Burch", "Levy", "Rasmussen",
			"Coffey", "Ponce", "Faulkner", "Donaldson", "Schmitt", "Novak", "Costa", "Montes", "Booker", "Cordova",
			"Waller", "Arellano", "Maddox", "Mata", "Bonilla", "Stanton", "Compton", "Kaufman", "Dudley", "Mcpherson",
			"Beltran", "Dickson", "Mccann", "Villegas", "Proctor", "Hester", "Cantrell", "Daugherty", "Cherry", "Bray",
			"Davila", "Rowland", "Levine", "Madden", "Spence", "Good", "Irwin", "Werner", "Krause", "Petty", "Whitney",
			"Baird", "Hooper", "Pollard", "Zavala", "Jarvis", "Holden", "Haas", "Hendrix", "Mcgrath", "Bird", "Lucero",
			"Terrell", "Riggs", "Joyce", "Mercer", "Rollins", "Galloway", "Duke", "Odom", "Andersen", "Downs",
			"Hatfield", "Benitez", "Archer", "Huerta", "Travis", "Mcneil", "Hinton", "Zhang", "Hays", "Mayo", "Fritz",
			"Branch", "Mooney", "Ewing", "Ritter", "Esparza", "Frey", "Braun", "Gay", "Riddle", "Haney", "Kaiser",
			"Holder", "Chaney", "Mcknight", "Gamble", "Vang", "Cooley", "Carney", "Cowan", "Forbes", "Ferrell",
			"Davies", "Barajas", "Shea", "Osborn", "Bright", "Cuevas", "Bolton", "Murillo", "Lutz", "Duarte", "Kidd",
			"Key", "Cooke");

	/**
	 * 
	 * Zip codes by State
	 * 
	 * USPS Zip Code Locator
	 * 
	 * ID State Name ST Zip Min Zip Max <br>
	 * 6 Alaska AK 99501 99950 <br>
	 * 5 Alabama AL 35004 36925 <br>
	 * 69 Arkansas AR 71601 72959 <br>
	 * 8 Arkansas (Texarkana) AR 75502 75502 <br>
	 * 7 Arizona AZ 85001 86556 <br>
	 * 9 California CA 90001 96162 <br>
	 * 10 Colorado CO 80001 81658 <br>
	 * 11 Connecticut CT 6001 6389 <br>
	 * 73 Connecticut CT 6401 6928 <br>
	 * 13 Dist of Columbia DC 20001 20039 <br>
	 * 74 Dist of Columbia DC 20042 20599 <br>
	 * 63 Dist of Columbia DC 20799 20799 <br>
	 * 12 Delaware DE 19701 19980 <br>
	 * 14 Florida FL 32004 34997 <br>
	 * 15 Georgia GA 30001 31999 <br>
	 * 65 Georga (Atlanta) GA 39901 39901 <br>
	 * 16 Hawaii HI 96701 96898 <br>
	 * 20 Iowa IA 50001 52809 <br>
	 * 66 Iowa (OMAHA) IA 68119 68120 <br>
	 * 17 Idaho ID 83201 83876 <br>
	 * 18 Illinois IL 60001 62999 <br>
	 * 19 Indiana IN 46001 47997 <br>
	 * 21 Kansas KS 66002 67954 <br>
	 * 22 Kentucky KY 40003 42788 <br>
	 * 68 Louisiana LA 70001 71232 <br>
	 * 23 Louisiana LA 71234 71497 <br>
	 * 26 Massachusetts MA 1001 2791 <br>
	 * 58 Massachusetts (Andover) MA 5501 5544 <br>
	 * 77 Maryland MD 20331 20331 <br>
	 * 78 Maryland MD 20335 20797 <br>
	 * 25 Maryland MD 20812 21930 <br>
	 * 24 Maine ME 3901 4992 <br>
	 * 27 Michigan MI 48001 49971 <br>
	 * 28 Minnesota MN 55001 56763 <br>
	 * 57 kc96 DataMO MO 63001 65899 <br>
	 * 29 Mississippi MS 38601 39776 <br>
	 * 59 Mississippi(Warren) MS 71233 71233 <br>
	 * 31 Montana MT 59001 59937 <br>
	 * 38 North Carolina NC 27006 28909 <br>
	 * 39 North Dakota ND 58001 58856 <br>
	 * 67 Nebraska NE 68001 68118 <br>
	 * 32 Nebraska NE 68122 69367 <br>
	 * 34 New Hampshire NH 3031 3897 <br>
	 * 35 New Jersey NJ 7001 8989 <br>
	 * 36 New Mexico NM 87001 88441 <br>
	 * 33 Nevada NV 88901 89883 <br>
	 * 60 New York (Fishers Is) NY 6390 6390 <br>
	 * 37 New York NY 10001 14975 <br>
	 * 40 Ohio OH 43001 45999 <br>
	 * 70 Oklahoma OK 73001 73199 <br>
	 * 41 Oklahoma OK 73401 74966 <br>
	 * 42 Oregon OR 97001 97920 <br>
	 * 43 Pennsylvania PA 15001 19640 <br>
	 * 44 Puerto Rico PR 0 0 <br>
	 * 45 Rhode Island RI 2801 2940 <br>
	 * 46 South Carolina SC 29001 29948 <br>
	 * 47 South Dakota SD 57001 57799 <br>
	 * 48 Tennessee TN 37010 38589 <br>
	 * 49 Texas (Austin) TX 73301 73301 <br>
	 * 71 Texas TX 75001 75501 <br>
	 * 72 Texas TX 75503 79999 <br>
	 * 61 Texas (El Paso) TX 88510 88589 <br>
	 * 50 Utah UT 84001 84784 <br>
	 * 64 Virginia VA 20040 20041 <br>
	 * 75 Virginia VA 20040 20167 <br>
	 * 76 Virginia VA 20042 20042 <br>
	 * 52 Virginia VA 22001 24658 <br>
	 * 51 Vermont VT 5001 5495 <br>
	 * 62 Vermont VT 5601 5907 <br>
	 * 53 Washington WA 98001 99403 <br>
	 * 55 Wisconsin WI 53001 54990 <br>
	 * 54 West Virginia WV 24701 26886 <br>
	 * 56 Wyoming WY 82001 83128 <br>
	 * 
	 */
}
