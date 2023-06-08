package com.inHealth.server.utils;
public class Decoder {

    public static String decodeAnswer(String answerEncoded, String questionId) {
        if (answerEncoded == null) return "!Answer left empty!";

        if (questionId.equals("DBQ700")) {
            if (answerEncoded.equals("1.0")) {
                return "Excellent";
            } else if (answerEncoded.equals("2.0")) {
                return "Very good";
            } else if (answerEncoded.equals("3.0")) {
                return "Good";
            } else if (answerEncoded.equals("4.0")) {
                return "Fair";
            } else if (answerEncoded.equals("5.0")) {
                return "Poor";
            } else if (answerEncoded.equals("7.0")) {
                return "Refused";
            } else if (answerEncoded.equals("9.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("DBQ197")) {
            if (answerEncoded.equals("0.0")) {
                return "Never";
            } else if (answerEncoded.equals("1.0")) {
                return "Rarely - less than once a week";
            } else if (answerEncoded.equals("2.0")) {
                return "Sometimes - once a week or more, but less than once a day";
            } else if (answerEncoded.equals("3.0")) {
                return "Often - once a day or more";
            } else if (answerEncoded.equals("4.0")) {
                return "Varied";
            } else if (answerEncoded.equals("7.0")) {
                return "Refused";
            } else if (answerEncoded.equals("9.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("DBQ223A")) {

            if (answerEncoded.equals("10.0")) {
                return "Whole or regular";
            } else if (answerEncoded.equals("77.0")) {
                return "Refused";
            } else if (answerEncoded.equals("99.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("DBQ930") || questionId.equals("DBQ935") || questionId.equals("ALQ111") || questionId.equals("OSQ150")) {
            if (answerEncoded.equals("1.0")) {
                return "Yes";
            } else if (answerEncoded.equals("2.0")) {
                return "No";
            } else if (answerEncoded.equals("7.0")) {
                return "Refused";
            } else if (answerEncoded.equals("9.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("WHD080P")) {
            if (answerEncoded.equals("42.0")) {
                return "Started to smoke or began to smoke again";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("WHD080Q")) {
            if (answerEncoded.equals("42.0")) {
                return "Ate more fruits, vegetables, salads";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("ALQ121")) {
            if (answerEncoded.equals("0.0")) {
                return "Never in the last year";
            } else if (answerEncoded.equals("1.0")) {
                return "Every day";
            } else if (answerEncoded.equals("2.0")) {
                return "Nearly every day";
            } else if (answerEncoded.equals("3.0")) {
                return "3 to 4 times a week";
            } else if (answerEncoded.equals("4.0")) {
                return "2 times a week";
            } else if (answerEncoded.equals("5.0")) {
                return "Once a week";
            } else if (answerEncoded.equals("6.0")) {
                return "2 to 3 times a month";
            } else if (answerEncoded.equals("7.0")) {
                return "Once a month";
            } else if (answerEncoded.equals("8.0")) {
                return "7 to 11 times in the last year";
            } else if (answerEncoded.equals("9.0")) {
                return "3 to 6 times in the last year";
            } else if (answerEncoded.equals("10.0")) {
                return "1 to 2 times in the last year";
            } else if (answerEncoded.equals("77.0")) {
                return "Refused";
            } else if (answerEncoded.equals("99.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("AUQ054")) {
            if (answerEncoded.equals("1.0")) {
                return "Excellent";
            } else if (answerEncoded.equals("2.0")) {
                return "Good";
            } else if (answerEncoded.equals("3.0")) {
                return "A little trouble";
            } else if (answerEncoded.equals("4.0")) {
                return "Moderate hearing trouble";
            } else if (answerEncoded.equals("5.0")) {
                return "A lot of trouble";
            } else if (answerEncoded.equals("6.0")) {
                return "Deaf";
            } else if (answerEncoded.equals("77.0")) {
                return "Refused";
            } else if (answerEncoded.equals("99.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("KIQ005")) {
            if (answerEncoded.equals("1.0")) {
                return "Never";
            } else if (answerEncoded.equals("2.0")) {
                return "Less than once a month";
            } else if (answerEncoded.equals("3.0")) {
                return "A few times a month";
            } else if (answerEncoded.equals("4.0")) {
                return "A few times a week";
            } else if (answerEncoded.equals("5.0")) {
                return "A few times a week";
            } else if (answerEncoded.equals("7.0")) {
                return "Refused";
            } else if (answerEncoded.equals("9.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("KIQ480")) {
            if (answerEncoded.equals("0.0")) {
                return "0";
            } else if (answerEncoded.equals("1.0")) {
                return "1";
            } else if (answerEncoded.equals("2.0")) {
                return "2";
            } else if (answerEncoded.equals("3.0")) {
                return "3";
            } else if (answerEncoded.equals("4.0")) {
                return "4";
            } else if (answerEncoded.equals("5.0")) {
                return "5 or more";
            } else if (answerEncoded.equals("7.0")) {
                return "Refused";
            } else if (answerEncoded.equals("9.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("DPQ010") || questionId.equals("DPQ020") || questionId.equals("DPQ030") ||
                questionId.equals("DPQ040") || questionId.equals("DPQ050") ||
                questionId.equals("DPQ060") || questionId.equals("DPQ090")) {
            if (answerEncoded.equals("0.0")) {
                return "Not at all";
            } else if (answerEncoded.equals("1.0")) {
                return "Several days";
            } else if (answerEncoded.equals("2.0")) {
                return "More than half the days";
            } else if (answerEncoded.equals("3.0")) {
                return "Nearly every day";
            } else if (answerEncoded.equals("7.0")) {
                return "Refused";
            } else if (answerEncoded.equals("9.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        }
        else if (questionId.equals("SLQ120")) {
            if (answerEncoded.equals("0.0")) {
                return "Never";
            } else if (answerEncoded.equals("1.0")) {
                return "Rarely - 1 time a month";
            } else if (answerEncoded.equals("2.0")) {
                return "Sometimes - 2-4 times a month";
            } else if (answerEncoded.equals("3.0")) {
                return "Often- 5-15 times a month";
            } else if (answerEncoded.equals("4.0")) {
                return "Almost always - 16-30 times a month";
            } else if (answerEncoded.equals("7.0")) {
                return "Refused";
            } else if (answerEncoded.equals("9.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
        } else if (questionId.equals("MCQ230a")) {
            if (answerEncoded.equals("10.0")) {
                return "Bladder";
            } else if (answerEncoded.equals("11.0")) {
                return "Blood";
            } else if (answerEncoded.equals("12.0")) {
                return "Bone";
            } else if (answerEncoded.equals("13.0")) {
                return "Brain";
            } else if (answerEncoded.equals("14.0")) {
                return "Breast";
            } else if (answerEncoded.equals("15.0")) {
                return "Cervix (cervical)";
            } else if (answerEncoded.equals("16.0")) {
                return "Colon";
            } else if (answerEncoded.equals("17.0")) {
                return "Esophagus (esophageal)";
            } else if (answerEncoded.equals("18.0")) {
                return "Gallbladder";
            } else if (answerEncoded.equals("19.0")) {
                return "Kidney";
            } else if (answerEncoded.equals("20.0")) {
                return "Larynx/ windpipe";
            } else if (answerEncoded.equals("21.0")) {
                return "Leukemia";
            } else if (answerEncoded.equals("22.0")) {
                return "Liver";
            } else if (answerEncoded.equals("23.0")) {
                return "Lung";
            } else if (answerEncoded.equals("24.0")) {
                return "Lymphoma/ Hodgkin's disease";
            } else if (answerEncoded.equals("25.0")) {
                return "Melanoma";
            } else if (answerEncoded.equals("26.0")) {
                return "Mouth/tongue/lip";
            } else if (answerEncoded.equals("27.0")) {
                return "Nervous system";
            } else if (answerEncoded.equals("28.0")) {
                return "Ovary (ovarian)";
            } else if (answerEncoded.equals("29.0")) {
                return "Pancreas (pancreatic)";
            } else if (answerEncoded.equals("30.0")) {
                return "Prostate";
            } else if (answerEncoded.equals("31.0")) {
                return "Rectum (rectal)";
            } else if (answerEncoded.equals("32.0")) {
                return "Skin (non-melanoma)";
            } else if (answerEncoded.equals("33.0")) {
                return "Skin (don't know what kind)";
            } else if (answerEncoded.equals("34.0")) {
                return "Soft tissue (muscle or fat)";
            } else if (answerEncoded.equals("35.0")) {
                return "Stomach";
            } else if (answerEncoded.equals("36.0")) {
                return "Testis (testicular)";
            } else if (answerEncoded.equals("37.0")) {
                return "Thyroid";
            } else if (answerEncoded.equals("38.0")) {
                return "Uterus (uterine)";
            } else if (answerEncoded.equals("39.0")) {
                return "Other";
            } else if (answerEncoded.equals("66.0")) {
                return "More than 3 kinds";
            } else if (answerEncoded.equals("77.0")) {
                return "Refused";
            } else if (answerEncoded.equals("99.0")) {
                return "Don't know";
            } else if (answerEncoded.equals(".")) {
                return "Missing";
            }
            else {
                return "!None of the above!";
            }
        }
        return "!None of the above!";
    }
}
