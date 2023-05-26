import * as React from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { Image } from "expo-image";
import Banner from "../components/Banner";
import Menu from "../components/Menu";
import { useNavigation } from "@react-navigation/native";
import { Color, Border, FontFamily, FontSize } from "../GlobalStyles";

const Disease = () => {
  const navigation = useNavigation();

  return (
    <View style={styles.disease}>
      <View style={styles.frameParent}>
        <View>
          <Text style={[styles.predictiveInsights, styles.updatedTodayClr]}>
            Predictive Insights
          </Text>
        </View>
        <Banner />
        <View style={styles.frameGroup}>
        <View style={styles.notification1Parent}>
         <Menu/>
        </View>
          <Pressable
            style={[styles.rectangleParent, styles.groupChildLayout]}
            onPress={() => navigation.navigate("DiseaseII")}
          >
            <View style={[styles.groupChild, styles.groupLayout1]} />
            <View style={styles.ic24Bolt1Parent}>
              <Image
                style={styles.ic24Bolt1Icon}
                contentFit="cover"
                source={require("../assets/ic24bolt-1.png")}
              />
              <Text style={[styles.inactivity, styles.walkingTypo]}>
                Inactivity
              </Text>
            </View>
            <View style={[styles.parent, styles.parentPosition]}>
              <Text style={[styles.text, styles.textTypo]}>24</Text>
              <Text style={[styles.min, styles.minPosition]}> min</Text>
            </View>
          </Pressable>
          <View style={styles.updatedTodayParent}>
            <Text style={[styles.updatedToday, styles.textTypo]}>
              Updated Today
            </Text>
            <View style={styles.groupParent}>
              <Pressable
                style={[styles.rectangleGroup, styles.groupLayout]}
                onPress={() => navigation.navigate("DiseaseII")}
              >
                <View style={[styles.groupItem, styles.groupLayout]} />
                <View
                  style={[styles.ic24Heart1Parent, styles.ic24ParentPosition]}
                >
                  <Image
                    style={styles.ic24Bolt1Icon}
                    contentFit="cover"
                    source={require("../assets/ic24heart-1.png")}
                  />
                  <Text
                    style={[styles.factorOfRisk, styles.min1Typo]}
                  >{`Factor of risk `}</Text>
                </View>
                <View style={[styles.typeIParent, styles.parentPosition]}>
                  <Text style={[styles.typeI, styles.min1Typo]}>Type I</Text>
                  <Text style={[styles.min1, styles.min1Typo]}> min</Text>
                </View>
              </Pressable>
              <Pressable
                style={[styles.rectangleContainer, styles.groupInnerLayout]}
                onPress={() => navigation.navigate("DiseaseII")}
              >
                <View style={[styles.groupInner, styles.groupInnerLayout]} />
                <View
                  style={[styles.ic24Flag1Parent, styles.ic24ParentPosition]}
                >
                  <Image
                    style={styles.ic24Bolt1Icon}
                    contentFit="cover"
                    source={require("../assets/ic24flag-1.png")}
                  />
                  <Text style={[styles.walking, styles.kmTypo]}>Walking</Text>
                </View>
                <View style={[styles.group, styles.parentPosition]}>
                  <Text style={[styles.text1, styles.kmTypo]}>10</Text>
                  <Text style={[styles.km, styles.kmTypo]}> km</Text>
                </View>
              </Pressable>
            </View>
          </View>
          <Image
            style={styles.syncIcon}
            contentFit="cover"
            source={require("../assets/sync.png")}
          />
          <Pressable
            style={[styles.saturday, styles.saturdayLayout]}
            onPress={() => navigation.navigate("DiseaseII")}
          >
            <Image
              style={[styles.saturdayChild, styles.saturdayLayout]}
              contentFit="cover"
              source={require("../assets/rectangle-65.png")}
            />
            <Text style={styles.viewMore}>View More</Text>
          </Pressable>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  notification1Parent: {
    top: 400,
    position: "absolute",

  },
  updatedTodayClr: {
    color: Color.bl,
    textAlign: "left",
  },
  groupChildLayout: {
    height: 97,
    width: 302,
    position: "absolute",
  },
  groupLayout1: {
    opacity: 0.1,
    borderRadius: Border.br_xl,
  },
  walkingTypo: {
    marginLeft: 7,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  parentPosition: {
    height: 42,
    left: 34,
    position: "absolute",
  },
  textTypo: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    top: 0,
    position: "absolute",
  },
  minPosition: {
    width: 71,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    left: 0,
    position: "absolute",
  },
  groupLayout: {
    height: 94,
    width: 305,
    top: 0,
    position: "absolute",
  },
  ic24ParentPosition: {
    left: 36,
    flexDirection: "row",
    top: 16,
    alignItems: "center",
    position: "absolute",
  },
  min1Typo: {
    color: Color.palevioletred_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  groupInnerLayout: {
    height: 90,
    width: 305,
    left: 0,
    position: "absolute",
  },
  kmTypo: {
    color: Color.lightseagreen_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  saturdayLayout: {
    height: 40,
    width: 104,
    top: 0,
    position: "absolute",
  },
  predictiveInsights: {
    fontSize: FontSize.size_11xl,
    width: 295,
    textAlign: "left",
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    color: Color.bl,
  },
  groupChild: {
    backgroundColor: Color.mediumslateblue_100,
    left: 0,
    top: 0,
    opacity: 0.1,
    height: 97,
    width: 302,
    position: "absolute",
  },
  ic24Bolt1Icon: {
    width: 16,
    height: 16,
    overflow: "hidden",
  },
  inactivity: {
    color: Color.mediumslateblue_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  ic24Bolt1Parent: {
    left: 35,
    width: 207,
    flexDirection: "row",
    top: 16,
    alignItems: "center",
    position: "absolute",
  },
  text: {
    width: 108,
    left: 1,
    color: Color.mediumslateblue_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  min: {
    color: Color.mediumslateblue_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  parent: {
    top: 44,
    width: 109,
  },
  rectangleParent: {
    top: 266,
    left: 3,
  },
  updatedToday: {
    fontSize: FontSize.size_lg,
    opacity: 0.7,
    left: 1,
    textAlign: "left",
    color: Color.bl,
  },
  groupItem: {
    backgroundColor: Color.palevioletred_100,
    opacity: 0.1,
    borderRadius: Border.br_xl,
    left: 0,
  },
  factorOfRisk: {
    marginLeft: 7,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  ic24Heart1Parent: {
    width: 194,
  },
  typeI: {
    left: 2,
    width: 105,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    top: 0,
    position: "absolute",
  },
  min1: {
    width: 71,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    left: 0,
    position: "absolute",
  },
  typeIParent: {
    top: 42,
    width: 107,
  },
  rectangleGroup: {
    left: 1,
  },
  groupInner: {
    backgroundColor: Color.lightseagreen_100,
    opacity: 0.1,
    borderRadius: Border.br_xl,
    top: 0,
  },
  walking: {
    marginLeft: 7,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  ic24Flag1Parent: {
    width: 183,
  },
  text1: {
    width: 96,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    top: 0,
    position: "absolute",
    left: 3,
  },
  km: {
    width: 60,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    color: Color.lightseagreen_100,
    left: 0,
    position: "absolute",
  },
  group: {
    top: 39,
    width: 99,
  },
  rectangleContainer: {
    top: 109,
  },
  groupParent: {
    top: 47,
    height: 199,
    left: 0,
    width: 306,
    position: "absolute",
  },
  updatedTodayParent: {
    top: 7,
    height: 246,
    left: 0,
    width: 306,
    position: "absolute",
  },
  syncIcon: {
    top: 10,
    left: 276,
    width: 20,
    height: 20,
    opacity: 0.5,
    overflow: "hidden",
    position: "absolute",
  },
  saturdayChild: {
    borderRadius: Border.br_xl,
    height: 40,
    width: 104,
    left: 0,
  },
  viewMore: {
    top: 11,
    left: 17,
    fontSize: FontSize.textSmallTextSemiBold_size,
    letterSpacing: 0.2,
    textTransform: "capitalize",
    fontWeight: "600",
    fontFamily: FontFamily.montserratSemibold,
    color: Color.whiteColor,
    textAlign: "left",
    position: "absolute",
  },
  saturday: {
    left: 153,
  },
  frameGroup: {
    height: 441,
    marginTop: 22,
    width: 306,
  },
  frameParent: {
    top: 106,
    left: 30,
    justifyContent: "center",
    alignItems: "center",
    position: "absolute",
  },
  disease: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    width: "100%",
    height: 812,
  },
});

export default Disease;
