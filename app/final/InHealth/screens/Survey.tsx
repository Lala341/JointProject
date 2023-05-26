import React, { useState } from "react";
import { Text, StyleSheet, View, TextInput, Pressable } from "react-native";
import { Image } from "expo-image";
import DropDownPicker from "react-native-dropdown-picker";
import { Slider as RNESlider } from "@rneui/themed";
import { CheckBox as RNKCheckBox } from "@ui-kitten/components";
import { useNavigation } from "@react-navigation/native";
import { FontSize, Color, FontFamily } from "../GlobalStyles";
import Menu from "../components/Menu";

const Survey = () => {
  const [rectangleDropdownOpen, setRectangleDropdownOpen] = useState(false);
  const [rectangleDropdownValue, setRectangleDropdownValue] = useState("Happy");
  const [rectangleDropdownItems, setRectangleDropdownItems] = useState([
    { value: "Happy", label: "Happy" },
    { value: "Sad", label: "Sad" },
    { value: "Excited", label: "Excited" },
    { value: "Anxious", label: "Anxious" },
    { value: "Content", label: "Content" },
    { value: "Stressed", label: "Stressed" },
    { value: "Energetic", label: "Energetic" },
    { value: "Tired", label: "Tired" },
    { value: "Relaxed", label: "Relaxed" },
    { value: "Frustrated", label: "Frustrated" },
    { value: "Motivated", label: "Motivated" },
    { value: "Bored", label: "Bored" },
    { value: "Grateful", label: "Grateful" },
    { value: "Overwhelmed", label: "Overwhelmed" },
    { value: "Inspired", label: "Inspired" },
    { value: "Worried", label: "Worried" },
    { value: "Peaceful", label: "Peaceful" },
    { value: "Irritated", label: "Irritated" },
    { value: "Optimistic", label: "Optimistic" },
    { value: "Nervous", label: "Nervous" },
    { value: "Joyful", label: "Joyful" },
    { value: "Depressed", label: "Depressed" },
    { value: "Confident", label: "Confident" },
    { value: "Calm", label: "Calm" },
    { value: "Loved", label: "Loved" },
  ]);
  const [rectangleSliderValue, setRectangleSliderValue] = useState(10);
  const [rectangleCheckboxchecked, setRectangleCheckboxchecked] =
    useState(undefined);
  const [rectangleCheckbox1checked, setRectangleCheckbox1checked] =
    useState(undefined);
  const [rectangleCheckbox2checked, setRectangleCheckbox2checked] =
    useState(undefined);
  const [rectangleCheckbox3checked, setRectangleCheckbox3checked] =
    useState(undefined);
  const [rectangleCheckbox4checked, setRectangleCheckbox4checked] =
    useState(undefined);
  const navigation = useNavigation();

  return (
    <View style={styles.survey}>
      <View style={styles.groupParent}>
        <View style={styles.parentLayout1}>
          <Text style={styles.dailySurvey}>Daily Survey</Text>
          <Image
            style={[styles.image1Icon, styles.containerLayout]}
            contentFit="cover"
            source={require("../assets/image-1.png")}
          />
        </View>
        <View style={styles.frameParent}>
          <View style={styles.dropdata}>
            <Text style={styles.moodTypo}>Mood</Text>
            <View style={[styles.wrapper, styles.frameLayout]}>
              <DropDownPicker
                style={styles.dropdownpicker}
                open={rectangleDropdownOpen}
                setOpen={setRectangleDropdownOpen}
                value={rectangleDropdownValue}
                setValue={setRectangleDropdownValue}
                placeholder="Select a mood"
                items={rectangleDropdownItems}
                dropDownContainerStyle={
                  styles.rectangleDropdowndropDownContainer
                }
              />
            </View>
          </View>
          <View style={styles.weightWrapper}>
            <Text style={[styles.weight, styles.moodTypo]}>Weight</Text>
          </View>
          <TextInput
            style={[styles.frameChild, styles.frameLayout]}
            placeholder=" "
            keyboardType="decimal-pad"
            autoCapitalize="none"
          />
          <View style={styles.howWouldYouRateYourStressWrapper}>
            <Text style={styles.howWouldYouContainer}>
              {`How would you rate your stress `}level today?
            </Text>
          </View>
          <RNESlider
            style={[styles.frameItem, styles.frameLayout]}
            step={1}
            minimumValue
            maximumValue={100}
            value={rectangleSliderValue}
            onValueChange={setRectangleSliderValue}
            thumbStyle={styles.rectangleSliderts}
            thumbTintColor="#ff0000"
            minimumTrackTintColor="#3f3f3f"
            maximumTrackTintColor="#b3b3b3"
          />
          <View style={[styles.rectangleParent, styles.parentLayout]}>
            <RNKCheckBox
              style={[styles.groupChild, styles.groupChildPosition]}
              checked={rectangleCheckboxchecked}
              onChange={() =>
                setRectangleCheckboxchecked(!rectangleCheckboxchecked)
              }
            />
            <Text style={styles.howWouldYouContainer}>
              Did you have trouble sleeping last night?
            </Text>
          </View>
          <View
            style={[styles.didYouConsumeAnySugaryOrParent, styles.parentLayout]}
          >
            <Text
              style={styles.howWouldYouContainer}
            >{`Did you consume any sugary or 
high-fat foods or drinks?`}</Text>
            <RNKCheckBox
              style={[styles.groupItem, styles.groupChildPosition]}
              checked={rectangleCheckbox1checked}
              onChange={() =>
                setRectangleCheckbox1checked(!rectangleCheckbox1checked)
              }
            />
          </View>
          <View
            style={[styles.didYouConsumeAnySugaryOrParent, styles.parentLayout]}
          >
            <Text style={styles.howWouldYouContainer}>
              {`Did you consume enough fruits `}and vegetables?
            </Text>
            <RNKCheckBox
              style={[styles.groupItem, styles.groupChildPosition]}
              checked={rectangleCheckbox2checked}
              onChange={() =>
                setRectangleCheckbox2checked(!rectangleCheckbox2checked)
              }
            />
          </View>
          <View
            style={[
              styles.didYouEngageInAnyActivitiParent,
              styles.parentLayout,
            ]}
          >
            <Text style={styles.howWouldYouContainer}>
              {`Did you engage in any activities `}
              {`that helped you relax or reduce `}stress?
            </Text>
            <RNKCheckBox
              style={[styles.rectangleRnkcheckbox, styles.groupChildPosition]}
              checked={rectangleCheckbox3checked}
              onChange={() =>
                setRectangleCheckbox3checked(!rectangleCheckbox3checked)
              }
            />
          </View>
          <View
            style={[styles.haveYouNoticedAnyChangesParent, styles.parentLayout]}
          >
            <Text style={styles.howWouldYouContainer}>
              {`Have you noticed any changes `}in your appetite or dietary
              habits recently?
            </Text>
            <RNKCheckBox
              style={[styles.groupChild1, styles.groupChildPosition]}
              checked={rectangleCheckbox4checked}
              onChange={() =>
                setRectangleCheckbox4checked(!rectangleCheckbox4checked)
              }
            />
          </View>
        </View>
      </View>
      <View style={styles.notification1Parent}>
         <Menu/>
        </View>
    </View>
  );
};

const styles = StyleSheet.create({
  dropdata:{

    position:"relative",
    zIndex: 1000,
  }
  ,
  rectangleDropdowndropDownContainer: {
    backgroundColor: "#d9d9d9",
  },
  rectangleSliderts: {
    height: 25,
    width: 25,
  },
  containerLayout: {
    width: 56,
    height: 56,
  },
  frameLayout: {
    height: 32,
    width: 297,
  },
  moodTypo: {
    textAlign: "left",
    fontSize: FontSize.size_lg,
    color: Color.bl,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  parentLayout: {
    width: 297,
    marginTop: 10,
  },
  groupChildPosition: {
    left: 270,
    position: "absolute",
  },
  parentLayout1: {
    height: 56,
    width: 295,
  },
  containerPosition: {
    left: "50%",
    position: "absolute",
  },
  iconLayout: {
    opacity: 0.5,
    overflow: "hidden",
    height: "100%",
    width: "100%",
  },
  dailySurvey: {
    fontSize: 32,
    textAlign: "left",
    color: Color.bl,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    left: 0,
    top: 0,
    width: 295,
    position: "absolute",
  },
  image1Icon: {
    left: 239,
    top: 0,
    width: 56,
    position: "absolute",
  },
  dropdownpicker: {
    backgroundColor: Color.gainsboro,

  },
  wrapper: {
    marginTop: 10,
    marginBottom: 15,

  },
  weight: {
    left: 0,
    textAlign: "center",
    top: 0,
    position: "absolute",
  },
  weightWrapper: {
    height: 23,
    marginTop: 10,
  },
  frameChild: {
    backgroundColor: Color.gainsboro,
    marginTop: 10,
    borderRadius: 7,
height: 20,
  },
  howWouldYouContainer: {
    fontSize: FontSize.size_lg,
    textAlign: "left",
    color: Color.bl,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    left: 0,
    top: 0,
    position: "absolute",
  },
  howWouldYouRateYourStressWrapper: {
    width: 270,
    height: 46,
    marginTop: 10,
  },
  frameItem: {
    marginTop: 10,
  },
  groupChild: {
    top: 26,
  },
  rectangleParent: {
    height: 54,
  },
  groupItem: {
    top: 32,
  },
  didYouConsumeAnySugaryOrParent: {
    height: 60,
  },
  rectangleRnkcheckbox: {
    top: 50,
  },
  didYouEngageInAnyActivitiParent: {
    height: 78,
  },
  groupChild1: {
    top: 58,
  },
  haveYouNoticedAnyChangesParent: {
    height: 86,
  },
  frameParent: {
    marginTop: 10,
  },
  groupParent: {
    top: 51,
    left: 38,
    position: "absolute",
  },
  icon: {
    marginLeft: 62.5,
  },
  notification1: {
    bottom: 16,
    width: 24,
    height: 24,
  },
  icon1: {
    marginLeft: 123.5,
  },
  frameInner: {
    marginLeft: -137.5,
    bottom: 2,
    width: 4,
    height: 4,
  },
  icon2: {
    marginLeft: -28.5,
    height: "100%",
    width: "100%",
  },
  container: {
    bottom: 0,
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
    top: 770,
    left:30,
    position: "absolute",

  },
  survey: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    height: 812,
    width: "100%",
  },
});

export default Survey;
