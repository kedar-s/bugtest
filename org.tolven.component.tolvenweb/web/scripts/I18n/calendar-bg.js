// ** I18N 

// Calendar BG Bulgarian language
// Author: Mihai Bazon, <mihai_bazon@yahoo.com>
// Translator: Valentin Sheiretsky, <valio@valio.eu.org>
// Encoding: Windows-1251
// Distributed under the same terms as the calendar itself.

// For translators: please use UTF-8 if possible.  We strongly believe that
// Unicode is the answer to a real internationalized world.  Also please
// include your contact information in the header, as can be seen above.

// full day names
Calendar._DN = new Array
("Íåäåëÿ",
 "Ïîíåäåëíèê",
 "Âòîðíèê",
 "Ñðÿäà",
 "×åòâúðòúê",
 "Ïåòúê",
 "Ñúáîòà",
 "Íåäåëÿ");

// Please note that the following array of short day names (and the same goes
// for short month names, _SMN) isn't absolutely necessary.  We give it here
// for exemplification on how one can customize the short day names, but if
// they are simply the first N letters of the full name you can simply say:
//
//   Calendar._SDN_len = N; // short day name length
//   Calendar._SMN_len = N; // short month name length
//
// If N = 3 then this is not needed either since we assume a value of 3 if not
// present, to be compatible with translation files that were written before
// this feature.

// short day names
Calendar._SDN = new Array
("Íåä",
 "Ïîí",
 "Âòî",
 "Ñðÿ",
 "×åò",
 "Ïåò",
 "Ñúá",
 "Íåä");

// full month names
Calendar._MN = new Array
("ßíóàðè",
 "Ôåâðóàðè",
 "Ìàðò",
 "Àïðèë",
 "Ìàé",
 "Þíè",
 "Þëè",
 "Àâãóñò",
 "Ñåïòåìâðè",
 "Îêòîìâðè",
 "Íîåìâðè",
 "Äåêåìâðè");

// short month names
Calendar._SMN = new Array
("ßíó",
 "Ôåâ",
 "Ìàð",
 "Àïð",
 "Ìàé",
 "Þíè",
 "Þëè",
 "Àâã",
 "Ñåï",
 "Îêò",
 "Íîå",
 "Äåê");

// tooltips
Calendar._TT["bg"] = [];
Calendar._TT["bg"][INFO] = "Èíôîðìàöèÿ çà êàëåíäàðà";

Calendar._TT["bg"][ABOUT] =
"DHTML Date/Time Selector\n" +
"(c) dynarch.com 2002-2005 / Author: Mihai Bazon\n" + // don't translate this this ;-)
"For latest version visit: http://www.dynarch.com/projects/calendar/\n" +
"Distributed under GNU LGPL.  See http://gnu.org/licenses/lgpl.html for details." +
"\n\n" +
"Date selection:\n" +
"- Use the \xab, \xbb buttons to select year\n" +
"- Use the " + String.fromCharCode(0x2039) + ", " + String.fromCharCode(0x203a) + " buttons to select month\n" +
"- Hold mouse button on any of the above buttons for faster selection.";
Calendar._TT["bg"][ABOUT_TIME] = "\n\n" +
"Time selection:\n" +
"- Click on any of the time parts to increase it\n" +
"- or Shift-click to decrease it\n" +
"- or click and drag for faster selection.";

Calendar._TT["bg"][PREV_YEAR] = "Ïðåäíà ãîäèíà (çàäðúæòå çà ìåíþ)";
Calendar._TT["bg"][PREV_MONTH] = "Ïðåäåí ìåñåö (çàäðúæòå çà ìåíþ)";
Calendar._TT["bg"][GO_TODAY] = "Èçáåðåòå äíåñ";
Calendar._TT["bg"][NEXT_MONTH] = "Ñëåäâàù ìåñåö (çàäðúæòå çà ìåíþ)";
Calendar._TT["bg"][NEXT_YEAR] = "Ñëåäâàùà ãîäèíà (çàäðúæòå çà ìåíþ)";
Calendar._TT["bg"][SEL_DATE] = "Èçáåðåòå äàòà";
Calendar._TT["bg"][DRAG_TO_MOVE] = "Ïðåìåñòâàíå";
Calendar._TT["bg"][PART_TODAY] = " (äíåñ)";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT["bg"][DAY_FIRST] = "%s êàòî ïúðâè äåí";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT["bg"][WEEKEND] = "0,6";

Calendar._TT["bg"][CLOSE] = "Çàòâîðåòå";
Calendar._TT["bg"][TODAY] = "Äíåñ";
Calendar._TT["bg"][TIME_PART] = "(Shift-)Click èëè drag çà äà ïðîìåíèòå ñòîéíîñòòà";

// date formats
Calendar._TT["bg"][DEF_DATE_FORMAT] = "%d/%m/%Y";
Calendar._TT["bg"][TT_DATE_FORMAT] = "%A - %e %B %Y";
Calendar._TT["bg"][DEF_TIME_FORMAT] = "%H:%M";

Calendar._TT["bg"][WK] = "Ñåäì";
Calendar._TT["bg"][TIME] = "×àñ:";
