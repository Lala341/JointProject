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
      
    
    
    </View>
  );
});

const styles = StyleSheet.create({
  wrapperPosition: {
    
  },
  iconLayout: {
    opacity: 0.5,
    overflow: "hidden",
    height: "100%",
    width: "100%",
  },
  icon: {
  },
  notification1: {
    margin: 16,
    width: 30,
    height: 30,
  },
  icon1: {
  },
  frameChild: {
    
    width: 4,
    height: 4,
  },
  icon2: {
   height: "100%",
    width: "100%",
  },
  wrapper: {
    top: 0,
    width: 56,
    height: 56,
  },
  icon3: {
    overflow: "hidden",
    height: "100%",
    width: "100%",
  },
  icon4: {
  },
  notification1Parent: {
    left: 6,
    width: "100%",
    height: 56,
    zIndex: 10001,
    flexDirection: "row",
    alignItems: "center",

    
  },
});

export default Menu;
