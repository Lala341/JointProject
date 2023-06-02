import React, { useMemo } from "react";
import { Pressable, StyleSheet, Text, View } from "react-native";
import { Image } from "expo-image";
import { FontFamily, FontSize, Border, Color } from "../GlobalStyles";

type ChangeSpecializationType = {
  overview?: string;

  /** Style props */
  propLeft?: number | string;

  /** Action props */
  onSaturdayPress?: () => void;
  onSaturdayPress1?: () => void;
};

const getStyleValue = (key: string, value: string | number | undefined) => {
  if (value === undefined) return;
  return { [key]: value === "unset" ? undefined : value };
};
const ChangeSpecialization = ({
  overview,
  propLeft,
  onSaturdayPress,
  onSaturdayPress1,
}: ChangeSpecializationType) => {
  const overviewStyle = useMemo(() => {
    return {
      ...getStyleValue("left", propLeft),
    };
  }, [propLeft]);

  return (
    <View style={styles.saturdayParent}>
      <Pressable
        style={[styles.saturday, styles.saturdayLayout]}
        onPress={onSaturdayPress}
      >
        <Image
          style={[styles.saturdayChild, styles.saturdayLayout]}
          contentFit="cover"
          source={require("../assets/rectangle-653.png")}
        />
        <Text style={[styles.overview, styles.backTypo, overviewStyle]}>
          {overview}
        </Text>
      </Pressable>
      <Pressable
        style={[styles.saturday1, styles.saturdayLayout]}
        onPress={onSaturdayPress1}
      >
        <Image
          style={[styles.saturdayChild, styles.saturdayLayout]}
          contentFit="cover"
          source={require("../assets/rectangle-654.png")}
        />
        <Text style={[styles.back, styles.backTypo]}>Back</Text>
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
  backTypo: {
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
  overview: {
    left: 22,
    color: Color.whiteColor,
  },
  saturday: {
    left: 0,
    width: 104,
    top: 0,
    position: "absolute",
  },
  back: {
    left: 36,
    color: Color.black,
  },
  saturday1: {
    left: 120,
    width: 104,
    top: 0,
    position: "absolute",
  },
  saturdayParent: {
    width: 224,
    marginTop: 20,
    height: 40,
  },
});

export default ChangeSpecialization;
