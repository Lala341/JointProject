import * as React from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { FontSize, FontFamily, Color } from "../GlobalStyles";

const Intro = () => {
  const navigation = useNavigation();

  return (
    <View style={[styles.intro, styles.introFlexBox]}>
      <Pressable
        style={[styles.inhealthWrapper, styles.introFlexBox]}
        onPress={() => navigation.navigate("Welcom")}
      >
        <Text style={styles.inhealth}>InHealth</Text>
      </Pressable>
    </View>
  );
};

const styles = StyleSheet.create({
  introFlexBox: {
    justifyContent: "center",
    alignItems: "center",
  },
  inhealth: {
    fontSize: FontSize.size_17xl,
    fontWeight: "700",
    fontFamily: FontFamily.dMSansBold,
    color: Color.bl,
    textAlign: "center",
    flex: 1,
  },
  inhealthWrapper: {
    width: "100%",
    flexDirection: "row",
  },
  intro: {
    backgroundColor: Color.whiteColor,
    width: "100%",
    height: 811,
    overflow: "hidden",
    flex: 1,
  },
});

export default Intro;
