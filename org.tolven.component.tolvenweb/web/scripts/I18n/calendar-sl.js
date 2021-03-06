/* Slovenian language file for the DHTML Calendar version 0.9.2 
* Author David Milost <mercy@volja.net>, January 2004.
* Feel free to use this script under the terms of the GNU Lesser General
* Public License, as long as you do not remove or alter this notice.
*/
 // full day names
Calendar._DN = new Array
("Nedelja",
 "Ponedeljek",
 "Torek",
 "Sreda",
 "Četrtek",
 "Petek",
 "Sobota",
 "Nedelja");
 // short day names
 Calendar._SDN = new Array
("Ned",
 "Pon",
 "Tor",
 "Sre",
 "Čet",
 "Pet",
 "Sob",
 "Ned");
// short month names
Calendar._SMN = new Array
("Jan",
 "Feb",
 "Mar",
 "Apr",
 "Maj",
 "Jun",
 "Jul",
 "Avg",
 "Sep",
 "Okt",
 "Nov",
 "Dec");
  // full month names
Calendar._MN = new Array
("Januar",
 "Februar",
 "Marec",
 "April",
 "Maj",
 "Junij",
 "Julij",
 "Avgust",
 "September",
 "Oktober",
 "November",
 "December");

// tooltips
Calendar._TT["sl"] = [];
Calendar._TT["sl"][INFO] = "O koledarju";

Calendar._TT["sl"][ABOUT] =
"DHTML Date/Time Selector\n" +
"(c) dynarch.com 2002-2005 / Author: Mihai Bazon\n" + // don't translate this this ;-)
"Za zadnjo verzijo pojdine na naslov: http://www.dynarch.com/projects/calendar/\n" +
"Distribuirano pod GNU LGPL.  Poglejte http://gnu.org/licenses/lgpl.html za podrobnosti." +
"\n\n" +
"Izbor datuma:\n" +
"- Uporabite \xab, \xbb gumbe za izbor leta\n" +
"- Uporabite " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " gumbe za izbor meseca\n" +
"- Zadržite klik na kateremkoli od zgornjih gumbov za hiter izbor.";
Calendar._TT["sl"][ABOUT_TIME] = "\n\n" +
"Izbor ćasa:\n" +
"- Kliknite na katerikoli del ćasa za poveć. le-tega\n" +
"- ali Shift-click za zmanj. le-tega\n" +
"- ali kliknite in povlecite za hiter izbor.";

Calendar._TT["sl"][TOGGLE] = "Spremeni dan s katerim se prićne teden";
Calendar._TT["sl"][PREV_YEAR] = "Predhodnje leto (dolg klik za meni)";
Calendar._TT["sl"][PREV_MONTH] = "Predhodnji mesec (dolg klik za meni)";
Calendar._TT["sl"][GO_TODAY] = "Pojdi na tekoći dan";
Calendar._TT["sl"][NEXT_MONTH] = "Naslednji mesec (dolg klik za meni)";
Calendar._TT["sl"][NEXT_YEAR] = "Naslednje leto (dolg klik za meni)";
Calendar._TT["sl"][SEL_DATE] = "Izberite datum";
Calendar._TT["sl"][DRAG_TO_MOVE] = "Pritisni in povleci za spremembo pozicije";
Calendar._TT["sl"][PART_TODAY] = " (danes)";
Calendar._TT["sl"][MON_FIRST] = "Prikaži ponedeljek kot prvi dan";
Calendar._TT["sl"][SUN_FIRST] = "Prikaži nedeljo kot prvi dan";
Calendar._TT["sl"][CLOSE] = "Zapri";
Calendar._TT["sl"][TODAY] = "Danes";

// date formats
Calendar._TT["sl"][DEF_DATE_FORMAT] = "%d.%m.%y";
Calendar._TT["sl"][TT_DATE_FORMAT] = "%a, %b %e";
Calendar._TT["sl"][DEF_TIME_FORMAT] = "%H:%M";

Calendar._TT["sl"][WK] = "Ted";