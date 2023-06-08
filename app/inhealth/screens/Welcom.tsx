import * as React from "react";
import { Image } from "expo-image";
import { StyleSheet, Text, Pressable, View } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { FontSize, FontFamily, Color } from "../GlobalStyles";

const Welcom = () => {
  const navigation = useNavigation();

  return (
    <View style={styles.welcom}>
      <View style={styles.groupParent}>
        <Image
          style={styles.frameChild}
          contentFit="cover"
          source={require("../assets/group-1000000737.png")}
        />
        <Text style={styles.welcomeToYour}>{`Welcome to
your personal health tracker`}</Text>
        <Pressable
          style={styles.wrapper}
          onPress={() => navigation.navigate("Survey")}
        >
          <Image
            style={styles.icon}
            contentFit="cover"
            source={require("../assets/group-101.png")}
          />
        </Pressable>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  frameChild: {
    width: 375,
    height: 443,
  },
  welcomeToYour: {
    fontSize: FontSize.size_17xl,
    fontWeight: "700",
    fontFamily: FontFamily.dMSansBold,
    color: Color.bl,
    textAlign: "left",
    width: 295,
    marginTop: 69,
  },
  icon: {
    height: "100%",
    width: "100%",
  },
  wrapper: {
    width: 56,
    height: 56,
    marginTop: 69,
  },
  groupParent: {
    position: "absolute",
    marginTop: -388,
    marginLeft: -187.5,
    top: "50%",
    left: "50%",
    alignItems: "flex-end",
    justifyContent: "center",
  },
  welcom: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    height: 812,
    width: "100%",
  },
});

export default Welcom;
