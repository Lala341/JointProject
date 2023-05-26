import * as React from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { Image } from "expo-image";
import ChangeIntervalQuery from "../components/ChangeIntervalQuery";
import ValueSteps from "../components/ValueSteps";
import GraphStatistics from "../components/GraphStatistics";
import { useNavigation } from "@react-navigation/native";
import { Border, FontSize, FontFamily, Color } from "../GlobalStyles";

const Statistics = () => {
  const navigation = useNavigation();

  return (
    <View style={styles.statistics}>
      <View style={[styles.frame, styles.frameFlexBox]}>
        <View style={styles.statisticsWrapper}>
          <Text style={[styles.statistics1, styles.statistics1FlexBox]}>
            Statistics
          </Text>
        </View>
        <ChangeIntervalQuery rectangle65MarginTop={20} />
        <View style={[styles.frameInner, styles.frameInnerLayout]}>
          <View style={[styles.groupParent, styles.wrapperPosition]}>
            <View style={[styles.rectangleParent, styles.rectangleLayout]}>
              <View style={[styles.groupChild, styles.groupLayout]} />
              <View style={[styles.ic24Heart1Parent, styles.frameFlexBox]}>
                <Image
                  style={styles.ic24Heart1Icon}
                  contentFit="cover"
                  source={require("../assets/ic24heart-11.png")}
                />
                <Text style={[styles.activity, styles.activityTypo]}>
                  Activity
                </Text>
              </View>
              <View style={styles.parent}>
                <Text style={[styles.text, styles.textTypo]}>78</Text>
                <Text style={[styles.min, styles.minPosition]}> min</Text>
              </View>
            </View>
            <View style={[styles.rectangleGroup, styles.rectangleLayout]}>
              <View style={[styles.groupItem, styles.groupLayout]} />
              <View style={[styles.ic24Heart1Parent, styles.frameFlexBox]}>
                <Image
                  style={styles.ic24Heart1Icon}
                  contentFit="cover"
                  source={require("../assets/ic24bolt-11.png")}
                />
                <Text style={[styles.inactivity, styles.min1Clr]}>
                  Inactivity
                </Text>
              </View>
              <View style={styles.parent}>
                <Text style={[styles.text1, styles.min1Clr]}>24</Text>
                <Text style={[styles.min1, styles.min1Clr]}> min</Text>
              </View>
            </View>
          </View>
        </View>
        <ValueSteps />
        <GraphStatistics />
        <Pressable
          style={[styles.saturday, styles.saturdayLayout]}
          onPress={() => navigation.navigate("StatisticsII")}
        >
          <Image
            style={[styles.saturdayChild, styles.saturdayLayout]}
            contentFit="cover"
            source={require("../assets/rectangle-652.png")}
          />
          <Text style={[styles.viewDetails, styles.statistics1FlexBox]}>
            View Details
          </Text>
        </Pressable>
        <View style={styles.notification1Parent}>
          <Pressable
            style={styles.graph1IconPosition}
            onPress={() => navigation.navigate("Disease")}
          >
            <Image
              style={[styles.icon, styles.iconCommon]}
              contentFit="cover"
              source={require("../assets/notification-11.png")}
            />
          </Pressable>
          <Pressable
            style={styles.graph1IconPosition}
            onPress={() => navigation.navigate("Profile")}
          >
            <Image
              style={[styles.icon1, styles.iconCommon]}
              contentFit="cover"
              source={require("../assets/profile-11.png")}
            />
          </Pressable>
          <Image
            style={styles.frameChild}
            contentFit="cover"
            source={require("../assets/ellipse-737.png")}
          />
          <Pressable
            style={[styles.wrapper, styles.wrapperPosition]}
            onPress={() => navigation.navigate("Survey")}
          >
            <Image
              style={[styles.icon2, styles.iconLayout]}
              contentFit="cover"
              source={require("../assets/group-20.png")}
            />
          </Pressable>
          <Pressable
            style={styles.graph1IconPosition}
            onPress={() => navigation.navigate("Home")}
          >
            <Image
              style={[styles.icon3, styles.iconLayout]}
              contentFit="cover"
              source={require("../assets/category-1.png")}
            />
          </Pressable>
          <Image
            style={[styles.graph1Icon, styles.iconCommon]}
            contentFit="cover"
            source={require("../assets/graph-11.png")}
          />
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  frameFlexBox: {
    alignItems: "center",
    position: "absolute",
  },
  statistics1FlexBox: {
    textAlign: "left",
    position: "absolute",
  },
  frameInnerLayout: {
    height: 138,
    width: 334,
  },
  wrapperPosition: {
    top: 0,
    position: "absolute",
  },
  rectangleLayout: {
    width: 155,
    top: 0,
    height: 138,
    position: "absolute",
  },
  groupLayout: {
    opacity: 0.1,
    borderRadius: Border.br_xl,
    width: 155,
    left: 0,
    top: 0,
    height: 138,
    position: "absolute",
  },
  activityTypo: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  textTypo: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    height: 60,
    left: 0,
    top: 0,
    position: "absolute",
  },
  minPosition: {
    height: 24,
    width: 36,
    fontFamily: FontFamily.dMSansRegular,
    left: 51,
    top: 24,
    fontSize: FontSize.size_base,
    position: "absolute",
  },
  min1Clr: {
    color: Color.mediumslateblue_100,
    textAlign: "left",
  },
  saturdayLayout: {
    height: 40,
    width: 137,
  },
  iconCommon: {
    opacity: 0.5,
    overflow: "hidden",
  },
  iconLayout: {
    height: "100%",
    width: "100%",
  },
  statistics1: {
    top: 5,
    left: 20,
    fontSize: FontSize.size_11xl,
    color: Color.bl,
    width: 295,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    textAlign: "left",
  },
  statisticsWrapper: {
    width: 375,
    height: 50,
  },
  groupChild: {
    backgroundColor: Color.palevioletred_100,
  },
  ic24Heart1Icon: {
    width: 16,
    height: 16,
    overflow: "hidden",
  },
  activity: {
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  ic24Heart1Parent: {
    top: 18,
    left: 18,
    flexDirection: "row",
  },
  text: {
    width: 53,
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  min: {
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  parent: {
    top: 61,
    left: 19,
    width: 87,
    height: 60,
    position: "absolute",
  },
  rectangleParent: {
    left: 0,
  },
  groupItem: {
    backgroundColor: Color.mediumslateblue_100,
  },
  inactivity: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  text1: {
    width: 55,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    height: 60,
    left: 0,
    top: 0,
    position: "absolute",
  },
  min1: {
    height: 24,
    width: 36,
    fontFamily: FontFamily.dMSansRegular,
    left: 51,
    top: 24,
    fontSize: FontSize.size_base,
    position: "absolute",
  },
  rectangleGroup: {
    left: 179,
  },
  groupParent: {
    left: 0,
    height: 138,
    width: 334,
  },
  frameInner: {
    marginTop: 20,
  },
  saturdayChild: {
    borderRadius: Border.br_xl,
    height: 40,
    width: 137,
    left: 0,
    top: 0,
    position: "absolute",
  },
  viewDetails: {
    top: 13,
    left: 29,
    fontSize: FontSize.textSmallTextSemiBold_size,
    letterSpacing: 0.2,
    textTransform: "capitalize",
    fontWeight: "600",
    fontFamily: FontFamily.montserratSemibold,
    color: Color.whiteColor,
  },
  saturday: {
    marginTop: 20,
  },
  icon: {
    marginLeft: 62.5,
    height: "100%",
    width: "100%",
  },
  graph1IconPosition: {
    width: 24,
    top: 16,
    height: 24,
    left: "50%",
    position: "absolute",
  },
  icon1: {
    marginLeft: 123.5,
    height: "100%",
    width: "100%",
  },
  frameChild: {
    marginLeft: -137.5,
    top: 50,
    width: 4,
    height: 4,
    left: "50%",
    position: "absolute",
  },
  icon2: {
    marginLeft: -28.5,
  },
  wrapper: {
    width: 56,
    height: 56,
    left: "50%",
  },
  icon3: {
    marginLeft: -147.5,
    overflow: "hidden",
  },
  graph1Icon: {
    marginLeft: -87.5,
    width: 24,
    top: 16,
    height: 24,
    left: "50%",
    position: "absolute",
  },
  notification1Parent: {
    height: 56,
    marginTop: 20,
    width: 295,
  },
  frame: {
    marginTop: -367,
    marginLeft: -187.5,
    top: "50%",
    justifyContent: "center",
    left: "50%",
  },
  statistics: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    height: 812,
    width: "100%",
  },
});

export default Statistics;
