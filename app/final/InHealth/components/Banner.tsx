import * as React from "react";
import { StyleSheet, View, Text, Pressable } from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { Image } from "expo-image";
import { useNavigation } from "@react-navigation/native";
import { Color, FontFamily, FontSize, Border, Padding } from "../GlobalStyles";

const Banner = () => {
  const navigation = useNavigation();

  return (
    <Pressable
      style={styles.banner}
      onPress={() => navigation.navigate("DiseaseII")}
    >
      <View style={[styles.bannerBackground, styles.baPosition]}>
        <LinearGradient
          style={[styles.ba, styles.baPosition]}
          locations={[0, 1]}
          colors={["#92a3fd", "#9dceff"]}
        />
      </View>
      <Image
        style={[styles.bannerDotsIcon, styles.bannerIconLayout]}
        contentFit="cover"
        source={require("../assets/bannerdots.png")}
      />
      <View style={[styles.bannerText, styles.buttonPosition]}>
        <Text style={[styles.bmiBodyMass, styles.button1Typo]}>
          BMI (Body Mass Index)
        </Text>
        <Text style={[styles.youHaveA, styles.textTypo]}>
          You have a normal weight
        </Text>
      </View>
      <LinearGradient
        style={[styles.button, styles.buttonPosition]}
        locations={[0, 1]}
        colors={["#c58bf2", "#eea4ce"]}
      >
        <Text style={[styles.button1, styles.button1Typo]}>View More</Text>
      </LinearGradient>
      <View style={styles.bannerPie}>
        <Image
          style={[styles.bannerPieEllipseIcon, styles.bannerIconLayout]}
          contentFit="cover"
          source={require("../assets/bannerpieellipse.png")}
        />
        <View style={styles.bannerPieText}>
          <Text style={[styles.text, styles.textTypo]}>20,1</Text>
        </View>
      </View>
    </Pressable>
  );
};

const styles = StyleSheet.create({
  baPosition: {
    left: "0%",
    top: "0%",
    position: "absolute",
  },
  bannerIconLayout: {
    maxHeight: "100%",
    overflow: "hidden",
    maxWidth: "100%",
    position: "absolute",
  },
  buttonPosition: {
    left: "6.35%",
    position: "absolute",
  },
  button1Typo: {
    textAlign: "left",
    color: Color.whiteColor,
    fontFamily: FontFamily.textSmallTextSemiBold,
    fontWeight: "600",
  },
  textTypo: {
    lineHeight: 18,
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    color: Color.whiteColor,
    left: "0%",
    position: "absolute",
  },
  ba: {
    borderRadius: Border.br_3xl,
    shadowColor: "rgba(149, 173, 254, 0.3)",
    shadowOffset: {
      width: 0,
      height: 10,
    },
    shadowRadius: 22,
    elevation: 22,
    shadowOpacity: 1,
    backgroundColor: Color.blueLinear,
    bottom: "0%",
    right: "0%",
    width: "100%",
    height: "100%",
    left: "0%",
  },
  bannerBackground: {
    bottom: "0%",
    right: "0%",
    width: "100%",
    height: "100%",
    left: "0%",
  },
  bannerDotsIcon: {
    height: "112.33%",
    width: "109.21%",
    right: "-3.17%",
    bottom: "-12.33%",
    left: "-6.03%",
    top: "0%",
    maxHeight: "100%",
    overflow: "hidden",
    maxWidth: "100%",
  },
  bmiBodyMass: {
    fontSize: FontSize.textMediumTextSemiBold_size,
    lineHeight: 21,
    left: "0%",
    top: "0%",
    position: "absolute",
  },
  youHaveA: {
    top: "59.09%",
    fontFamily: FontFamily.textSmallTextRegular,
  },
  bannerText: {
    height: "30.14%",
    width: "51.43%",
    top: "17.81%",
    right: "42.22%",
    bottom: "52.05%",
  },
  button1: {
    fontSize: FontSize.textCaptionSemiBold_size,
    lineHeight: 15,
  },
  button: {
    height: "23.97%",
    width: "30.16%",
    top: "58.22%",
    right: "63.49%",
    bottom: "17.81%",
    borderRadius: Border.br_31xl,
    flexDirection: "row",
    paddingHorizontal: Padding.p_11xl,
    paddingVertical: Padding.p_3xs,
    alignItems: "center",
    justifyContent: "space-between",
    display: "none",
    backgroundColor: Color.blueLinear,
  },
  bannerPieEllipseIcon: {
    height: "158.49%",
    width: "158.49%",
    top: "-19.81%",
    right: "-29.25%",
    bottom: "-38.68%",
    left: "-29.25%",
  },
  text: {
    fontFamily: FontFamily.textSmallTextSemiBold,
    fontWeight: "600",
    fontSize: FontSize.textSmallTextSemiBold_size,
    top: "0%",
  },
  bannerPieText: {
    height: "16.98%",
    width: "21.7%",
    top: "18.87%",
    right: "18.87%",
    bottom: "64.15%",
    left: "59.43%",
    position: "absolute",
  },
  bannerPie: {
    height: "72.6%",
    width: "33.65%",
    top: "13.7%",
    right: "6.35%",
    bottom: "13.7%",
    left: "60%",
    position: "absolute",
  },
  banner: {
    width: 315,
    height: 146,
    marginTop: 22,
  },
});

export default Banner;
