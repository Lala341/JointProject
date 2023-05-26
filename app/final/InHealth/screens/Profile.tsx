import React, { useState } from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { Image } from "expo-image";
import { LinearGradient } from "expo-linear-gradient";
import { Toggle as RNKToggle } from "@ui-kitten/components";
import { useNavigation } from "@react-navigation/native";
import { Color, Border, FontFamily, FontSize } from "../GlobalStyles";
import Menu from "../components/Menu";

const Profile = () => {
  const [togglechecked, setTogglechecked] = useState(undefined);
  const navigation = useNavigation();

  return (
    <View style={styles.profile}>
    
      <View style={[styles.frameParent, styles.frameParentPosition]}>
        <View style={styles.profileWrapper}>
          <Text style={styles.profile1}>Profile</Text>
        </View>
        <View style={styles.profileSection}>
          <View style={[styles.personalData, styles.cardLayout]}>
            <View style={[styles.heightCard, styles.cardLayout]}>
              <View style={[styles.heightCard, styles.cardLayout]}>
                <View style={[styles.bgChild, styles.childShadowBox]} />
              </View>
              <View style={[styles.heightText, styles.textPosition1]}>
                <Text style={[styles.cm, styles.cmTypo]}>180cm</Text>
                <Text style={[styles.height, styles.heightTypo]}>Height</Text>
              </View>
            </View>
            <View style={[styles.weightCard, styles.cardLayout]}>
              <View style={[styles.heightCard, styles.cardLayout]}>
                <View style={[styles.bgItem, styles.childShadowBox]} />
              </View>
              <View style={[styles.weightText, styles.textPosition1]}>
                <Text style={[styles.kg, styles.cmTypo]}>65kg</Text>
                <Text style={[styles.weight, styles.heightTypo]}>Weight</Text>
              </View>
            </View>
            <View style={[styles.ageCard, styles.cardLayout]}>
              <View style={[styles.heightCard, styles.cardLayout]}>
                <View style={[styles.bgInner, styles.childShadowBox]} />
              </View>
              <View style={styles.ageText}>
                <Text style={[styles.text, styles.cmTypo]}>22</Text>
                <Text style={[styles.weight, styles.heightTypo]}>Age</Text>
              </View>
            </View>
          </View>
          <View style={[styles.name, styles.nameLayout]}>
            <Text style={[styles.laura, styles.lauraPosition]}>Laura</Text>
          </View>
          <Image
            style={[styles.latestPicIcon, styles.frameParentPosition]}
            contentFit="cover"
            source={require("../assets/latestpic.png")}
          />
          <Pressable style={[styles.button, styles.bg3Layout]}>
            <View style={[styles.bg3, styles.bg3Layout]}>
              <LinearGradient
                style={[styles.rectangleLineargradient, styles.bg3Layout]}
                locations={[0, 1]}
                colors={["#92a3fd", "#9dceff"]}
              />
            </View>
            <View style={styles.text1}>
              <Text style={styles.edit}>Edit</Text>
            </View>
          </Pressable>
        </View>
        <View style={styles.dataCardSection}>
          <View style={styles.rectangleViewPosition}>
            <View style={styles.rectangleViewPosition}>
              <View
                style={[styles.rectangleView, styles.rectangleViewPosition]}
              />
            </View>
            <View style={[styles.title, styles.titlePosition]}>
              <Text style={[styles.account, styles.lauraPosition]}>
                Account
              </Text>
            </View>
            <View style={[styles.personalData1, styles.settingLayout]}>
              <Image
                style={[styles.iconProfile, styles.settingLayout]}
                contentFit="cover"
                source={require("../assets/iconprofile.png")}
              />
              <View style={[styles.text2, styles.textPosition]}>
                <Text style={[styles.personalData2, styles.heightTypo]}>
                  Personal Data
                </Text>
              </View>
              <Image
                style={[styles.iconArrow, styles.iconLayout1]}
                contentFit="cover"
                source={require("../assets/iconarrow.png")}
              />
            </View>
          </View>
          <View style={[styles.notificationSection, styles.bg5Layout]}>
            <View style={[styles.bg5, styles.bg5Layout]}>
              <View style={[styles.bgChild1, styles.bg5Layout]} />
            </View>
            <View style={[styles.title1, styles.titlePosition]}>
              <Text style={[styles.account, styles.lauraPosition]}>
                Notification
              </Text>
            </View>
            <View style={[styles.personalData1, styles.settingLayout]}>
              <View style={[styles.text3, styles.textPosition]}>
                <Text style={[styles.personalData2, styles.heightTypo]}>
                  Pop-up Notification
                </Text>
              </View>
              <RNKToggle
                style={[styles.toggle, styles.textPosition]}
                checked={togglechecked}
                onChange={() => setTogglechecked(!togglechecked)}
              />
              <Image
                style={[styles.iconProfile, styles.settingLayout]}
                contentFit="cover"
                source={require("../assets/iconnotif.png")}
              />
            </View>
          </View>
          <View style={[styles.otherSection, styles.bg6Layout]}>
            <View style={[styles.bg6, styles.bg6Layout]}>
              <View style={[styles.bgChild2, styles.bg6Layout]} />
            </View>
            <View style={[styles.title2, styles.titlePosition]}>
              <Text style={[styles.account, styles.lauraPosition]}>Other</Text>
            </View>
            <View style={[styles.personalData1, styles.settingLayout]}>
              <View style={[styles.text4, styles.textPosition]}>
                <Text style={[styles.personalData2, styles.heightTypo]}>
                  Contact Us
                </Text>
              </View>
              <Image
                style={[styles.iconArrow, styles.iconLayout1]}
                contentFit="cover"
                source={require("../assets/iconarrow.png")}
              />
              <Image
                style={[styles.iconProfile, styles.settingLayout]}
                contentFit="cover"
                source={require("../assets/iconmessage.png")}
              />
            </View>
            <View style={[styles.setting, styles.settingLayout]}>
              <View style={[styles.text5, styles.textPosition]}>
                <Text style={[styles.personalData2, styles.heightTypo]}>
                  Settings
                </Text>
              </View>
              <Image
                style={[styles.iconArrow, styles.iconLayout1]}
                contentFit="cover"
                source={require("../assets/iconarrow.png")}
              />
              <Image
                style={[styles.iconProfile, styles.settingLayout]}
                contentFit="cover"
                source={require("../assets/iconsetting.png")}
              />
            </View>
            <View style={[styles.privacyPolicy, styles.settingLayout]}>
              <View style={[styles.text6, styles.text6Position]}>
                <Text style={[styles.personalData2, styles.heightTypo]}>
                  Privacy Policy
                </Text>
              </View>
              <Image
                style={[styles.iconArrow3, styles.text6Position]}
                contentFit="cover"
                source={require("../assets/iconarrow.png")}
              />
              <Image
                style={[styles.iconProfile, styles.settingLayout]}
                contentFit="cover"
                source={require("../assets/iconprivacy.png")}
              />
            </View>
          </View>
        </View>
        <View style={styles.notification1Parent}>
         <Menu/>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  fashionPosition: {
    color: Color.black,
    top: "50%",
    position: "absolute",
  },
  iconLayout2: {
    maxHeight: "100%",
    maxWidth: "100%",
    overflow: "hidden",
    position: "absolute",
  },
  nameLayout: {
    height: 30,
    position: "absolute",
  },
  frameParentPosition: {
    position: "absolute",
    left: 0,
  },
  cardLayout: {
    height: 65,
    position: "absolute",
  },
  childShadowBox: {
    shadowOpacity: 1,
    elevation: 40,
    shadowRadius: 40,
    shadowOffset: {
      width: 0,
      height: 10,
    },
    shadowColor: "rgba(29, 22, 23, 0.07)",
    borderRadius: Border.br_base,
    backgroundColor: Color.whiteColor,
  },
  textPosition1: {
    height: 44,
    top: 11,
    position: "absolute",
  },
  cmTypo: {
    fontFamily: FontFamily.poppinsMedium,
    fontWeight: "500",
  },
  heightTypo: {
    color: Color.gray1,
    fontFamily: FontFamily.textSmallTextRegular,
    lineHeight: 18,
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    position: "absolute",
  },
  lauraPosition: {
    color: Color.blackColor,
    top: 0,
    left: 0,
    textAlign: "left",
    position: "absolute",
  },
  bg3Layout: {
    width: 83,
    height: 30,
    position: "absolute",
  },
  rectangleViewPosition: {
    height: 102,
    top: 0,
    width: 315,
    left: 0,
    position: "absolute",
  },
  titlePosition: {
    height: 24,
    top: 20,
    left: 20,
    position: "absolute",
  },
  settingLayout: {
    height: 20,
    position: "absolute",
  },
  textPosition: {
    top: 1,
    height: 18,
    position: "absolute",
  },
  iconLayout1: {
    width: 18,
    left: 262,
  },
  bg5Layout: {
    height: 99,
    width: 315,
    left: 0,
    position: "absolute",
  },
  bg6Layout: {
    height: 159,
    width: 315,
    left: 0,
    position: "absolute",
  },
  text6Position: {
    top: 2,
    height: 18,
    position: "absolute",
  },
  iconLayout: {
    opacity: 0.5,
    height: "100%",
    overflow: "hidden",
    width: "100%",
  },
  wrapperPosition: {
    left: "50%",
    position: "absolute",
  },
  category1Position: {
    top: 16,
    left: "50%",
    height: 24,
    width: 24,
    position: "absolute",
  },
  fashion: {
    marginTop: 472,
    fontFamily: FontFamily.montserratMedium,
    textAlign: "left",
    fontWeight: "500",
    fontSize: FontSize.textMediumTextSemiBold_size,
    color: Color.black,
    left: "66.93%",
    top: "50%",
  },
  articles: {
    marginTop: 499,
    left: "66.67%",
    fontFamily: FontFamily.montserratSemibold,
    textAlign: "center",
    fontWeight: "600",
    fontSize: FontSize.size_5xl,
    color: Color.black,
    top: "50%",
  },
  ovalIcon: {
    height: "6.28%",
    width: "13.6%",
    top: "100.62%",
    right: "19.47%",
    bottom: "-6.9%",
    left: "66.93%",
    maxHeight: "100%",
    maxWidth: "100%",
  },
  runerSilhouetteRunningFastIcon: {
    top: 827,
    left: 261,
    width: 30,
    overflow: "hidden",
  },
  profile1: {
    top: 5,
    fontSize: FontSize.size_11xl,
    fontFamily: FontFamily.dMSansMedium,
    color: Color.bl,
    width: 295,
    left: 20,
    textAlign: "left",
    fontWeight: "500",
    position: "absolute",
  },
  profileWrapper: {
    width: 375,
    height: 50,
  },
  bgChild: {
    width: 95,
    top: 0,
    height: 65,
    position: "absolute",
    left: 0,
  },
  heightCard: {
    width: 95,
    top: 0,
    left: 0,
  },
  cm: {
    lineHeight: 21,
    fontFamily: FontFamily.poppinsMedium,
    top: 0,
    fontSize: FontSize.textMediumTextSemiBold_size,
    position: "absolute",
    left: 0,
    textAlign: "left",
  },
  height: {
    left: 3,
    top: 26,
    color: Color.gray1,
    fontFamily: FontFamily.textSmallTextRegular,
  },
  heightText: {
    left: 24,
  },
  bgItem: {
    width: 95,
    top: 0,
    height: 65,
    position: "absolute",
    left: 0,
  },
  kg: {
    left: 3,
    lineHeight: 21,
    fontFamily: FontFamily.poppinsMedium,
    top: 0,
    fontSize: FontSize.textMediumTextSemiBold_size,
    position: "absolute",
    textAlign: "left",
  },
  weight: {
    top: 26,
    color: Color.gray1,
    fontFamily: FontFamily.textSmallTextRegular,
    left: 0,
  },
  weightText: {
    left: 26,
  },
  weightCard: {
    left: 110,
    width: 95,
    top: 0,
  },
  bgInner: {
    width: 95,
    top: 0,
    height: 65,
    position: "absolute",
    left: 0,
  },
  text: {
    left: 4,
    lineHeight: 21,
    fontFamily: FontFamily.poppinsMedium,
    top: 0,
    fontSize: FontSize.textMediumTextSemiBold_size,
    position: "absolute",
    textAlign: "center",
  },
  ageText: {
    left: 35,
    height: 44,
    top: 11,
    position: "absolute",
  },
  ageCard: {
    left: 220,
    width: 95,
    top: 0,
  },
  personalData: {
    top: 70,
    width: 315,
    left: 0,
  },
  laura: {
    lineHeight: 30,
    fontFamily: FontFamily.poppinsMedium,
    fontWeight: "500",
    fontSize: FontSize.size_5xl,
  },
  name: {
    left: 74,
    top: 10,
  },
  latestPicIcon: {
    width: 55,
    height: 55,
    top: 0,
    left: 0,
  },
  rectangleLineargradient: {
    borderRadius: 99,
    backgroundColor: Color.blueLinear,
    top: 0,
    left: 0,
  },
  bg3: {
    top: 0,
    left: 0,
  },
  edit: {
    color: Color.whiteColor,
    lineHeight: 18,
    fontSize: FontSize.textSmallTextSemiBold_size,
    fontFamily: FontFamily.poppinsMedium,
    top: 0,
    left: 0,
    textAlign: "left",
    fontWeight: "500",
    position: "absolute",
  },
  text1: {
    top: 6,
    height: 18,
    left: 30,
    position: "absolute",
  },
  button: {
    left: 232,
    top: 10,
  },
  profileSection: {
    height: 135,
    marginTop: 24,
    width: 315,
  },
  rectangleView: {
    shadowOpacity: 1,
    elevation: 40,
    shadowRadius: 40,
    shadowOffset: {
      width: 0,
      height: 10,
    },
    shadowColor: "rgba(29, 22, 23, 0.07)",
    borderRadius: Border.br_base,
    backgroundColor: Color.whiteColor,
  },
  account: {
    fontSize: FontSize.size_base,
    lineHeight: 24,
    fontFamily: FontFamily.textSmallTextSemiBold,
    fontWeight: "600",
  },
  title: {
  },
  iconProfile: {
    width: 20,
    top: 0,
    left: 0,
  },
  personalData2: {
    top: 0,
    left: 0,
  },
  text2: {
    left: 30,
  },
  iconArrow: {
    top: 1,
    height: 18,
    position: "absolute",
  },
  personalData1: {
    top: 59,
    width: 280,
    height: 20,
    left: 20,
  },
  bgChild1: {
    shadowOpacity: 1,
    elevation: 40,
    shadowRadius: 40,
    shadowOffset: {
      width: 0,
      height: 10,
    },
    shadowColor: "rgba(29, 22, 23, 0.07)",
    borderRadius: Border.br_base,
    backgroundColor: Color.whiteColor,
    top: 0,
  },
  bg5: {
    top: 0,
  },
  title1: {
  },
  text3: {
    left: 30,
  },
  toggle: {
    left: 244,
    width: 36,
  },
  notificationSection: {
    top: 124,
  },
  bgChild2: {
    shadowOpacity: 1,
    elevation: 40,
    shadowRadius: 40,
    shadowOffset: {
      width: 0,
      height: 10,
    },
    shadowColor: "rgba(29, 22, 23, 0.07)",
    borderRadius: Border.br_base,
    backgroundColor: Color.whiteColor,
    top: 0,
  },
  bg6: {
    top: 0,
  },
  title2: {
  },
  text4: {
    left: 30,
  },
  text5: {
    left: 30,
  },
  setting: {
    top: 119,
    width: 280,
    height: 20,
    left: 20,
  },
  text6: {
    left: 30,
  },
  iconArrow3: {
    width: 18,
    left: 262,
  },
  privacyPolicy: {
    top: 88,
    width: 280,
    height: 20,
    left: 20,
  },
  otherSection: {
    top: 252,
  },
  dataCardSection: {
    height: 411,
    marginTop: 24,
    width: 315,
  },
  icon: {
    marginLeft: 62.5,
  },
  icon1: {
    marginLeft: 123.5,
  },
  frameChild: {
    marginLeft: -137.5,
    top: 50,
    width: 4,
    height: 4,
  },
  icon2: {
    marginLeft: -28.5,
    height: "100%",
    width: "100%",
  },
  wrapper: {
    width: 56,
    height: 56,
    top: 0,
  },
  groupIcon: {
    height: "83.33%",
    width: "83.33%",
    top: "8.33%",
    right: "8.33%",
    bottom: "8.33%",
    left: "8.33%",
  },
  category1: {
    marginLeft: -147.5,
    overflow: "hidden",
  },
  icon3: {
    marginLeft: -87.5,
  },
  notification1Parent: {
    top: 700,
    position: "absolute",

  },
  frameParent: {
    top: 44,
    alignItems: "center",
    justifyContent: "center",
    left: 0,
  },
  profile: {
    flex: 1,
    height: 812,
    width: "100%",
    backgroundColor: Color.whiteColor,
  },
});

export default Profile;
