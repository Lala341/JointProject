import React, { memo } from "react";
import { Pressable, StyleSheet, View } from "react-native";
import { Image } from "expo-image";
import { useNavigation } from "@react-navigation/native";

const Menu = memo(() => {
  const navigation = useNavigation();

  return (
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
          style={styles.icon2}
          contentFit="cover"
          source={require("../assets/group-20.png")}
        />
      </Pressable>
      <Pressable
        style={[styles.notification1, styles.wrapperPosition]}
        onPress={() => navigation.navigate("Home")}
      >
        <Image
          style={styles.icon3}
          contentFit="cover"
          source={require("../assets/category-1.png")}
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
  );
});

const styles = StyleSheet.create({
  wrapperPosition: {
    left: "50%",
    position: "absolute",
  },
  iconLayout: {
    opacity: 0.5,
    overflow: "hidden",
    height: "100%",
    width: "100%",
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
    height: "100%",
    width: "100%",
  },
  wrapper: {
    top: 0,
    width: 56,
    height: 56,
  },
  icon3: {
    marginLeft: -147.5,
    overflow: "hidden",
    height: "100%",
    width: "100%",
  },
  icon4: {
    marginLeft: -87.5,
  },
  notification1Parent: {
    top: 385,
    left: 6,
    width: 295,
    height: 56,
    position: "absolute",
  },
});

export default Menu;
