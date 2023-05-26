import * as React from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { Image } from "expo-image";
import { useNavigation } from "@react-navigation/native";
import { Color, FontFamily, FontSize, Border } from "../GlobalStyles";

const DiseaseII = () => {
  const navigation = useNavigation();

  return (
    <View style={styles.diseaseIi}>
      <View style={styles.frameParent}>
        <View style={styles.frameGroup}>
          <View>
            <Text
              style={[styles.predictiveInsights, styles.recommendationsTypo]}
            >
              Predictive Insights
            </Text>
          </View>
          <Text style={[styles.recommendations, styles.recommendationsTypo]}>
            Recommendations
          </Text>
          <View style={[styles.fullbodyCard, styles.bgLayout]}>
            <View style={[styles.bg, styles.childPosition]}>
              <LinearGradient
                style={[styles.bgChild, styles.childPosition]}
                locations={[0, 1]}
                colors={["#92a3fd", "#9dceff"]}
              />
            </View>
            <View style={[styles.workoutText, styles.workoutPosition]}>
              <Text style={[styles.diet, styles.dietTypo]}>Diet</Text>
              <Text
                style={[
                  styles.dietRecommendationsBasedOnContainer,
                  styles.exercises40minsTypo,
                ]}
              >
                Diet recommendations basedon your profile.
              </Text>
            </View>
            <Image
              style={[styles.ellipseIcon, styles.workoutPosition]}
              contentFit="cover"
              source={require("../assets/ellipse.png")}
            />
            <Pressable style={[styles.buttonViewmore, styles.buttonLayout]}>
              <View style={[styles.buttonBg, styles.buttonLayout]}>
                <View style={[styles.buttonBgChild, styles.buttonLayout]} />
              </View>
              <View style={styles.buttonText}>
                <Text style={[styles.viewMore, styles.dietTypo]}>
                  View more
                </Text>
              </View>
            </Pressable>
            <Image
              style={styles.vectorIcon}
              contentFit="cover"
              source={require("../assets/vector.png")}
            />
          </View>
          <View style={[styles.fullbodyCard, styles.bgLayout]}>
            <View style={[styles.bg, styles.childPosition]}>
              <LinearGradient
                style={[styles.bgChild, styles.childPosition]}
                locations={[0, 1]}
                colors={["#92a3fd", "#9dceff"]}
              />
            </View>
            <View style={[styles.workoutText1, styles.workoutPosition]}>
              <Text style={[styles.diet, styles.dietTypo]}>Workout</Text>
              <Text
                style={[styles.exercises40mins, styles.exercises40minsTypo]}
              >
                12 Exercises | 40mins
              </Text>
            </View>
            <Image
              style={[styles.ellipseIcon, styles.workoutPosition]}
              contentFit="cover"
              source={require("../assets/ellipse.png")}
            />
            <Pressable style={[styles.buttonViewmore, styles.buttonLayout]}>
              <View style={[styles.buttonBg, styles.buttonLayout]}>
                <View style={[styles.buttonBgChild, styles.buttonLayout]} />
              </View>
              <View style={styles.buttonText}>
                <Text style={[styles.viewMore, styles.dietTypo]}>
                  View more
                </Text>
              </View>
            </Pressable>
            <Image
              style={styles.vectorIcon1}
              contentFit="cover"
              source={require("../assets/vector1.png")}
            />
          </View>
        </View>
        <View style={styles.notification1Parent}>
          <Pressable
            style={[styles.notification1, styles.wrapperPosition]}
            onPress={() => navigation.navigate("Disease")}
          >
            <Image
              style={[styles.icon, styles.iconLayout]}
              contentFit="cover"
              source={require("../assets/notification-1.png")}
            />
          </Pressable>
          <Pressable
            style={[styles.notification1, styles.wrapperPosition]}
            onPress={() => navigation.navigate("Profile")}
          >
            <Image
              style={[styles.icon1, styles.iconLayout]}
              contentFit="cover"
              source={require("../assets/profile-1.png")}
            />
          </Pressable>
          <Image
            style={[styles.frameChild, styles.wrapperPosition]}
            contentFit="cover"
            source={require("../assets/ellipse-737.png")}
          />
          <Pressable
            style={[styles.wrapper, styles.wrapperPosition]}
            onPress={() => navigation.navigate("Survey")}
          >
            <Image
              style={[styles.icon2, styles.iconLayout1]}
              contentFit="cover"
              source={require("../assets/group-20.png")}
            />
          </Pressable>
          <Pressable
            style={[styles.notification1, styles.wrapperPosition]}
            onPress={() => navigation.navigate("Home")}
          >
            <Image
              style={[styles.icon3, styles.iconLayout1]}
              contentFit="cover"
              source={require("../assets/category-11.png")}
            />
          </Pressable>
          <Pressable
            style={[styles.notification1, styles.wrapperPosition]}
            onPress={() => navigation.navigate("Statistics")}
          >
            <Image
              style={[styles.icon4, styles.iconLayout]}
              contentFit="cover"
              source={require("../assets/graph-1.png")}
            />
          </Pressable>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  recommendationsTypo: {
    textAlign: "left",
    color: Color.bl,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    width: 295,
  },
  bgLayout: {
    height: 132,
    width: 315,
  },
  childPosition: {
    left: 0,
    top: 0,
  },
  workoutPosition: {
    top: 20,
    position: "absolute",
  },
  dietTypo: {
    fontFamily: FontFamily.poppinsMedium,
    left: 0,
    top: 0,
    textAlign: "left",
    fontWeight: "500",
    position: "absolute",
  },
  exercises40minsTypo: {
    color: Color.gray1,
    fontFamily: FontFamily.textSmallTextRegular,
    lineHeight: 18,
    fontSize: FontSize.textSmallTextSemiBold_size,
    left: 0,
    textAlign: "left",
    position: "absolute",
  },
  buttonLayout: {
    height: 35,
    width: 94,
    position: "absolute",
  },
  wrapperPosition: {
    left: "50%",
    position: "absolute",
  },
  iconLayout: {
    opacity: 0.5,
    height: "100%",
    overflow: "hidden",
    width: "100%",
  },
  iconLayout1: {
    height: "100%",
    width: "100%",
  },
  predictiveInsights: {
    fontSize: FontSize.size_11xl,
    width: 295,
  },
  recommendations: {
    fontSize: FontSize.size_xl,
    marginTop: 19,
    width: 295,
  },
  bgChild: {
    borderRadius: Border.br_xl,
    opacity: 0.2,
    backgroundColor: Color.blueLinear,
    height: 132,
    width: 315,
    position: "absolute",
  },
  bg: {
    height: 132,
    width: 315,
    position: "absolute",
  },
  diet: {
    fontSize: FontSize.textMediumTextSemiBold_size,
    lineHeight: 21,
    color: Color.blackColor,
  },
  dietRecommendationsBasedOnContainer: {
    top: 19,
  },
  workoutText: {
    width: 180,
    height: 55,
    left: 20,
  },
  ellipseIcon: {
    left: 203,
    width: 92,
    height: 92,
  },
  buttonBgChild: {
    borderRadius: Border.br_31xl,
    left: 0,
    top: 0,
    backgroundColor: Color.whiteColor,
    width: 94,
  },
  buttonBg: {
    left: 0,
    top: 0,
  },
  viewMore: {
    fontSize: FontSize.textCaptionSemiBold_size,
    lineHeight: 15,
  },
  buttonText: {
    top: 10,
    width: 54,
    height: 15,
    left: 20,
    position: "absolute",
  },
  buttonViewmore: {
    top: 79,
    left: 20,
  },
  vectorIcon: {
    height: "80.25%",
    width: "23.53%",
    top: "10.61%",
    right: "9.17%",
    bottom: "9.14%",
    left: "67.3%",
    maxWidth: "100%",
    maxHeight: "100%",
    overflow: "hidden",
    position: "absolute",
  },
  fullbodyCard: {
    marginTop: 19,
  },
  exercises40mins: {
    top: 26,
  },
  workoutText1: {
    width: 123,
    height: 44,
    left: 20,
  },
  vectorIcon1: {
    left: 216,
    width: 73,
    height: 90,
    top: 19,
    position: "absolute",
  },
  frameGroup: {
    alignItems: "center",
  },
  icon: {
    marginLeft: 62.5,
  },
  notification1: {
    top: 16,
    width: 24,
    height: 24,
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
  },
  wrapper: {
    width: 56,
    height: 56,
    top: 0,
    left: "50%",
  },
  icon3: {
    marginLeft: -147.5,
    overflow: "hidden",
  },
  icon4: {
    marginLeft: -87.5,
  },
  notification1Parent: {
    marginTop: 221,
    height: 56,
    width: 295,
  },
  frameParent: {
    top: 106,
    left: 30,
    justifyContent: "center",
    alignItems: "center",
    position: "absolute",
  },
  diseaseIi: {
    flex: 1,
    height: 812,
    width: "100%",
    backgroundColor: Color.whiteColor,
  },
});

export default DiseaseII;
