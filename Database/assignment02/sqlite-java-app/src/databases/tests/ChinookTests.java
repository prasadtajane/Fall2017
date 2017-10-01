package databases.tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.Permission;

import databases.ChinookApp;
import junit.framework.TestCase;

/**
 * Tests the ChinookTests program using
 * the included Chinook database
 * 
 * @author derbinsky
 */
public class ChinookTests extends TestCase {
	
	private static String DB_PATH = ChinookTests.class.getResource("Chinook_Sqlite_AutoIncrementPKs.sqlite").toString();

	@SuppressWarnings("serial")
	private static class ExitException extends SecurityException {}
	
	private static class NoExitSecurityManager extends SecurityManager 
    {
        @Override
        public void checkPermission(Permission perm) {}
        
        @Override
        public void checkPermission(Permission perm, Object context) {}
        
        @Override
        public void checkExit(int status) { super.checkExit(status); throw new ExitException(); }
    }
	
	@Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        System.setSecurityManager(new NoExitSecurityManager());
    }
	
	@Override
    protected void tearDown() throws Exception 
    {
        System.setSecurityManager(null);
        super.tearDown();
    }
	
	private static void _test(String[] input, String expectedOutput) {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(os));
		try {
			ChinookApp.main(input);
		} catch (Exception e) {}
		System.setOut(null);
		
		assertEquals("Output Error!", String.format(expectedOutput), os.toString());
	}
	
	private static void _testOne(String input, int value) {
		_test(new String[] {DB_PATH, "1", input}, String.format("%d%n", value));
	}
	
	public static void testOne() {
		_testOne("USA", 13);
		_testOne("Brazil", 5);
		_testOne("Belgium", 1);
		_testOne("United Kingdom", 3);
		_testOne("Canada", 8);
		_testOne("USSR", 0);
	}
	
	public static void testTwo() {
		final String[] result = {
			"1. Andrew Adams (General Manager)",
			"2. Nancy Edwards (Sales Manager)",
			"3. Jane Peacock (Sales Support Agent)",
			"4. Margaret Park (Sales Support Agent)",
			"5. Steve Johnson (Sales Support Agent)",
			"6. Michael Mitchell (IT Manager)",
			"7. Robert King (IT Staff)",
			"8. Laura Callahan (IT Staff)"
		};
		
		_test(new String[] {DB_PATH, "2"}, String.format(String.join("%n", result) + "%n"));
	}
	
	private static void _testThree(int input, int value) {
		_test(new String[] {DB_PATH, "3", String.valueOf(input)}, String.format("%d%n", value));
	}
	
	public static void testThree() {
		_testThree(0, 0);
		_testThree(1, 0);
		_testThree(2, 0);
		_testThree(3, 21);
		_testThree(4, 20);
		_testThree(5, 18);
		_testThree(6, 0);
		_testThree(7, 0);
		_testThree(8, 0);
		_testThree(9, 0);
	}
	
	public static void testFour() {
		final String[] result = {
			"1. Luís Gonçalves (São José dos Campos, SP, Brazil)",
			"2. Leonie Köhler (Stuttgart, Germany)",
			"3. François Tremblay (Montréal, QC, Canada)",
			"4. Bjørn Hansen (Oslo, Norway)",
			"5. František Wichterlová (Prague, Czech Republic)",
			"6. Helena Holý (Prague, Czech Republic)",
			"7. Astrid Gruber (Vienne, Austria)",
			"8. Daan Peeters (Brussels, Belgium)",
			"9. Kara Nielsen (Copenhagen, Denmark)",
			"10. Eduardo Martins (São Paulo, SP, Brazil)",
			"11. Alexandre Rocha (São Paulo, SP, Brazil)",
			"12. Roberto Almeida (Rio de Janeiro, RJ, Brazil)",
			"13. Fernanda Ramos (Brasília, DF, Brazil)",
			"14. Mark Philips (Edmonton, AB, Canada)",
			"15. Jennifer Peterson (Vancouver, BC, Canada)",
			"16. Frank Harris (Mountain View, CA, USA)",
			"17. Jack Smith (Redmond, WA, USA)",
			"18. Michelle Brooks (New York, NY, USA)",
			"19. Tim Goyer (Cupertino, CA, USA)",
			"20. Dan Miller (Mountain View, CA, USA)",
			"21. Kathy Chase (Reno, NV, USA)",
			"22. Heather Leacock (Orlando, FL, USA)",
			"23. John Gordon (Boston, MA, USA)",
			"24. Frank Ralston (Chicago, IL, USA)",
			"25. Victor Stevens (Madison, WI, USA)",
			"26. Richard Cunningham (Fort Worth, TX, USA)",
			"27. Patrick Gray (Tucson, AZ, USA)",
			"28. Julia Barnett (Salt Lake City, UT, USA)",
			"29. Robert Brown (Toronto, ON, Canada)",
			"30. Edward Francis (Ottawa, ON, Canada)",
			"31. Martha Silk (Halifax, NS, Canada)",
			"32. Aaron Mitchell (Winnipeg, MB, Canada)",
			"33. Ellie Sullivan (Yellowknife, NT, Canada)",
			"34. João Fernandes (Lisbon, Portugal)",
			"35. Madalena Sampaio (Porto, Portugal)",
			"36. Hannah Schneider (Berlin, Germany)",
			"37. Fynn Zimmermann (Frankfurt, Germany)",
			"38. Niklas Schröder (Berlin, Germany)",
			"39. Camille Bernard (Paris, France)",
			"40. Dominique Lefebvre (Paris, France)",
			"41. Marc Dubois (Lyon, France)",
			"42. Wyatt Girard (Bordeaux, France)",
			"43. Isabelle Mercier (Dijon, France)",
			"44. Terhi Hämäläinen (Helsinki, Finland)",
			"45. Ladislav Kovács (Budapest, Hungary)",
			"46. Hugh O'Reilly (Dublin, Dublin, Ireland)",
			"47. Lucas Mancini (Rome, RM, Italy)",
			"48. Johannes Van der Berg (Amsterdam, VV, Netherlands)",
			"49. Stanisław Wójcik (Warsaw, Poland)",
			"50. Enrique Muñoz (Madrid, Spain)",
			"51. Joakim Johansson (Stockholm, Sweden)",
			"52. Emma Jones (London, United Kingdom)",
			"53. Phil Hughes (London, United Kingdom)",
			"54. Steve Murray (Edinburgh , United Kingdom)",
			"55. Mark Taylor (Sidney, NSW, Australia)",
			"56. Diego Gutiérrez (Buenos Aires, Argentina)",
			"57. Luis Rojas (Santiago, Chile)",
			"58. Manoj Pareek (Delhi, India)",
			"59. Puja Srivastava (Bangalore, India)"
		};
		
		_test(new String[] {DB_PATH, "4"}, String.format(String.join("%n", result) + "%n"));
	}
	
	private static void _testFive(int input, String[] value) {
		_test(new String[] {DB_PATH, "5", String.valueOf(input)}, String.format(String.join("%n", value) + "%n"));
	}
	
	public static void testFive() {
		_testFive(59, new String[] {
			"Invoice #23 ($3.96)",
			" 117: 'Cotton Fields' on 'Chronicle, Vol. 2' by 'Creedence Clearwater Revival' (1 @ $0.99)",
			" 118: 'Don't Look Now' on 'Chronicle, Vol. 2' by 'Creedence Clearwater Revival' (1 @ $0.99)",
			" 119: 'Before You Accuse Me' on 'Chronicle, Vol. 2' by 'Creedence Clearwater Revival' (1 @ $0.99)",
			" 120: 'Pagan Baby' on 'Chronicle, Vol. 2' by 'Creedence Clearwater Revival' (1 @ $0.99)",
			"",
			"Invoice #45 ($5.94)",
			" 235: 'Lightning Strikes Twice' on 'Virtual XI' by 'Iron Maiden' (1 @ $0.99)",
			" 236: 'Don't Look To The Eyes Of A Stranger' on 'Virtual XI' by 'Iron Maiden' (1 @ $0.99)",
			" 237: 'Night Train' on 'Sex Machine' by 'James Brown' (1 @ $0.99)",
			" 238: 'It's A Man's Man's Man's World' on 'Sex Machine' by 'James Brown' (1 @ $0.99)",
			" 239: 'Hey America' on 'Sex Machine' by 'James Brown' (1 @ $0.99)",
			" 240: 'Get Up Offa That Thing' on 'Sex Machine' by 'James Brown' (1 @ $0.99)",
			"",
			"Invoice #97 ($1.99)",
			" 530: 'Baltar's Escape' on 'Battlestar Galactica (Classic), Season 1' by 'Battlestar Galactica (Classic)' (1 @ $1.99)",
			"",
			"Invoice #218 ($1.98)",
			" 1179: 'Gates Of Urizen' on 'Chemical Wedding' by 'Bruce Dickinson' (1 @ $0.99)",
			" 1180: 'Trupets Of Jericho' on 'Chemical Wedding' by 'Bruce Dickinson' (1 @ $0.99)",
			"",
			"Invoice #229 ($13.86)",
			" 1238: 'Um Indio' on 'Minha Historia' by 'Chico Buarque' (1 @ $0.99)",
			" 1239: 'Fora Da Ordem' on 'Minha Historia' by 'Chico Buarque' (1 @ $0.99)",
			" 1240: 'Chão De Estrelas' on 'Minha História' by 'Os Mutantes' (1 @ $0.99)",
			" 1241: 'Stormbringer' on 'MK III The Final Concerts [Disc 1]' by 'Deep Purple' (1 @ $0.99)",
			" 1242: 'Houses Of The Holy' on 'Physical Graffiti [Disc 1]' by 'Led Zeppelin' (1 @ $0.99)",
			" 1243: 'Mangueira' on 'Sambas De Enredo 2001' by 'Various Artists' (1 @ $0.99)",
			" 1244: 'Love Of My Life' on 'Supernatural' by 'Santana' (1 @ $0.99)",
			" 1245: 'El Farol' on 'Supernatural' by 'Santana' (1 @ $0.99)",
			" 1246: 'Um Love' on 'The Best of Ed Motta' by 'Ed Motta' (1 @ $0.99)",
			" 1247: 'Jeru' on 'The Essential Miles Davis [Disc 1]' by 'Miles Davis' (1 @ $0.99)",
			" 1248: 'So What' on 'The Essential Miles Davis [Disc 1]' by 'Miles Davis' (1 @ $0.99)",
			" 1249: 'Black Satin' on 'The Essential Miles Davis [Disc 2]' by 'Miles Davis' (1 @ $0.99)",
			" 1250: 'Blue Rythm Fantasy' on 'Up An' Atom' by 'Gene Krupa' (1 @ $0.99)",
			" 1251: 'Bop Boogie' on 'Up An' Atom' by 'Gene Krupa' (1 @ $0.99)",
			"",
			"Invoice #284 ($8.91)",
			" 1533: 'Texarkana' on 'Out Of Time' by 'R.E.M. Feat. Kate Pearson' (1 @ $0.99)",
			" 1534: 'So Central Rain' on 'The Best Of R.E.M.: The IRS Years' by 'R.E.M.' (1 @ $0.99)",
			" 1535: 'Fall On Me' on 'The Best Of R.E.M.: The IRS Years' by 'R.E.M.' (1 @ $0.99)",
			" 1536: 'Infeliz Natal' on 'Cesta Básica' by 'Raimundos' (1 @ $0.99)",
			" 1537: 'Esporrei Na Manivela' on 'Cesta Básica' by 'Raimundos' (1 @ $0.99)",
			" 1538: 'No Fundo Do Quintal Da Escola' on 'Raul Seixas' by 'Raul Seixas' (1 @ $0.99)",
			" 1539: 'Que Luz É Essa' on 'Raul Seixas' by 'Raul Seixas' (1 @ $0.99)",
			" 1540: 'The Power Of Equality' on 'Blood Sugar Sex Magik' by 'Red Hot Chili Peppers' (1 @ $0.99)",
			" 1541: 'Mellowship Slinky In B Major' on 'Blood Sugar Sex Magik' by 'Red Hot Chili Peppers' (1 @ $0.99)",
			"",
		});
	}
	
}
