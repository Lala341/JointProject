import * as React from "react";
import { StyleSheet, View, Text } from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { Image } from "expo-image";
import { FontSize, Border, Color, FontFamily } from "../GlobalStyles";

const ValueSteps = () => {
  return (
    <View style={styles.rectangleParent}>
      <LinearGradient
        style={styles.rectangle}
        locations={[0, 1]}
        colors={["#673ab7", "#512da8"]}
      />
      <Text style={[styles.fashion, styles.kTypo]}>Total steps</Text>
      <Text style={[styles.articles, styles.textFlexBox]}>10088</Text>
      <Text style={[styles.k, styles.kTypo]}>(10K)</Text>
      <Image
        style={styles.groupChild}
        contentFit="cover"
        source={require("../assets/ellipse-21.png")}
      />
      <Image
        style={styles.groupItem}
        contentFit="cover"
        source={require("../assets/ellipse-20.png")}
      />
      <Image
        style={[styles.groupInner, styles.groupPosition]}
        contentFit="cover"
        source={require("../assets/ellipse-19.png")}
      />
      <Image
        style={styles.ellipseIcon}
        contentFit="cover"
        source={require("../assets/ellipse-17.png")}
      />
      <Image
        style={[styles.groupChild1, styles.groupPosition]}
        contentFit="cover"
        source={require("../assets/ellipse-18.png")}
      />
      <Text style={[styles.text, styles.textFlexBox]}>73%</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  kTypo: {
    textAlign: "left",
    fontSize: FontSize.textMediumTextSemiBold_size,
    position: "absolute",
  },
  textFlexBox: {
    textAlign: "center",
    position: "absolute",
  },
  groupPosition: {
    top: 3,
    position: "absolute",
  },
  rectangle: {
    height: "100%",
    width: "100%",
    top: "0%",
    right: "0%",
    bottom: "0%",
    left: "0%",
    borderRadius: Border.br_mini,
    backgroundColor: Color.blueLinear,
    position: "absolute",
  },
  fashion: {
    marginTop: -24,
    width: "32.14%",
    color: Color.whiteColor,
    left: "5.68%",
    top: "50%",
    fontFamily: FontFamily.montserratMedium,
    fontWeight: "500",
  },
  articles: {
    marginTop: -1,
    width: "24.03%",
    fontSize: FontSize.size_5xl,
    fontWeight: "600",
    fontFamily: FontFamily.montserratSemibold,
    color: Color.whiteColor,
    left: "5.68%",
    top: "50%",
  },
  k: {
    top: 47,
    left: 105,
    fontFamily: FontFamily.montserratRegular,
    color: Color.gray_200,
    width: 41,
  },
  groupChild: {
    top: 15,
    left: 229,
    width: 54,
    height: 51,
    opacity: 0.1,
    position: "absolute",
  },
  groupItem: {
    top: 4,
    left: 206,
    width: 93,
    height: 80,
    borderRadius: Border.br_8xs,
    position: "absolute",
  },
  groupInner: {
    left: 210,
    width: 97,
    height: 83,
    borderRadius: Border.br_8xs,
  },
  ellipseIcon: {
    top: -16,
    left: 235,
    width: 74,
    height: 103,
    borderRadius: Border.br_8xs,
    position: "absolute",
  },
  groupChild1: {
    left: 211,
    width: 88,
    height: 87,
  },
  text: {
    top: 35,
    left: 240,
    fontSize: FontSize.textSmallTextSemiBold_size,
    color: Color.textColor,
    width: 34,
    fontFamily: FontFamily.montserratMedium,
    fontWeight: "500",
  },
  rectangleParent: {
    width: 308,
    height: 82,
    marginTop: 20,
  },
});

export default ValueSteps;
