import React, { useMemo } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { Image } from "expo-image";
import { Color, FontFamily, FontSize, Border } from "../GlobalStyles";

type ChangeIntervalQueryType = {
  /** Style props */
  rectangle65MarginTop?: number | string;
};

const getStyleValue = (key: string, value: string | number | undefined) => {
  if (value === undefined) return;
  return { [key]: value === "unset" ? undefined : value };
};
const ChangeIntervalQuery = ({
  rectangle65MarginTop,
}: ChangeIntervalQueryType) => {
  const groupViewStyle = useMemo(() => {
    return {
      ...getStyleValue("marginTop", rectangle65MarginTop),
    };
  }, [rectangle65MarginTop]);

  return (
    <View style={[styles.saturdayParent, groupViewStyle]}>
      <Pressable style={[styles.saturday, styles.saturdayLayout]}>
        <Image
          style={[styles.saturdayChild, styles.saturdayLayout]}
          contentFit="cover"
          source={require("../assets/rectangle-65.png")}
        />
        <Text style={styles.daily}>Daily</Text>
      </Pressable>
      <Pressable style={[styles.saturday1, styles.saturdayLayout]}>
        <Image
          style={[styles.saturdayChild, styles.saturdayLayout]}
          contentFit="cover"
          source={require("../assets/rectangle-651.png")}
        />
        <Text style={[styles.weekly, styles.weeklyTypo]}>weekly</Text>
      </Pressable>
      <Pressable style={[styles.vectorParent, styles.saturdayLayout]}>
        <Image
          style={[styles.saturdayChild, styles.saturdayLayout]}
          contentFit="cover"
          source={require("../assets/rectangle-651.png")}
        />
        <Text style={[styles.monthly, styles.weeklyTypo]}>Monthly</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  saturdayLayout: {
    width: 104,
    top: 0,
    position: "absolute",
    height: 40,
  },
  weeklyTypo: {
    color: Color.black,
    textAlign: "left",
    fontFamily: FontFamily.montserratSemibold,
    fontWeight: "600",
    textTransform: "capitalize",
    letterSpacing: 0.2,
    fontSize: FontSize.textSmallTextSemiBold_size,
    top: 13,
    position: "absolute",
  },
  saturdayChild: {
    borderRadius: Border.br_xl,
    left: 0,
    width: 104,
    top: 0,
    position: "absolute",
  },
  daily: {
    left: 38,
    color: Color.whiteColor,
    textAlign: "left",
    fontFamily: FontFamily.montserratSemibold,
    fontWeight: "600",
    textTransform: "capitalize",
    letterSpacing: 0.2,
    fontSize: FontSize.textSmallTextSemiBold_size,
    top: 13,
    position: "absolute",
  },
  saturday: {
    left: 0,
    width: 104,
    top: 0,
    position: "absolute",
  },
  weekly: {
    left: 32,
  },
  saturday1: {
    left: 120,
    width: 104,
    top: 0,
    position: "absolute",
  },
  monthly: {
    left: 30,
  },
  vectorParent: {
    left: 239,
    width: 104,
    top: 0,
    position: "absolute",
  },
  saturdayParent: {
    width: 343,
    marginTop: 18,
    height: 40,
  },
});

export default ChangeIntervalQuery;
