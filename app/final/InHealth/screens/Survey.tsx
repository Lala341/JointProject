import React, { useEffect, useState } from "react";
import { Text, StyleSheet, View, TextInput, Pressable } from "react-native";
import { Image } from "expo-image";
import DropDownPicker from "react-native-dropdown-picker";
import { Slider as RNESlider } from "@rneui/themed";
import { CheckBox as RNKCheckBox } from "@ui-kitten/components";
import { useNavigation } from "@react-navigation/native";
import { FontSize, Color, FontFamily } from "../GlobalStyles";
import Menu from "../components/Menu";
import { Alert } from "react-native";

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
    useState(false);
  const [rectangleCheckbox1checked, setRectangleCheckbox1checked] =
    useState(false);
  const [rectangleCheckbox2checked, setRectangleCheckbox2checked] =
    useState(false);
  const [rectangleCheckbox3checked, setRectangleCheckbox3checked] =
    useState(false);
  const [rectangleCheckbox4checked, setRectangleCheckbox4checked] =
    useState(false);
  const navigation = useNavigation();
const [schema, setSchema]= useState(undefined);

const createTwoButtonAlert = () =>
Alert.alert('Success', 'Survey sent successfully.', [
  {text: 'OK', onPress: () => {console.log('OK Pressed');
  navigation.navigate("Home");}},
]);


useEffect(() => {
  if(schema===undefined){
    getSurvey();
  }
}, [schema]);
  const getSurvey = async () => {
   
      const options = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        }
      };
      const response = await fetch('http://192.168.219.23:8090/survey/daily', options);

      if (response.ok) {
        console.log('File sent to server');
        const data = await response.json();
        console.log(data);
        setSchema(data);
      } else {

        console.error('Failed to send file to server');
      }
    
  };
  const getStructureSurvey=()=>{
    
    var data=[{
     "id": "97b37294-5a29-47c1-81c5-1a7b46fe65af",
       "text": "Mood",
      "answer": rectangleDropdownValue
  },
  {
    "id": "4ff71448-0e1b-4e93-a9e8-a93497a3d94b",
    "text": "Weight",
      "answer": "10"
  },
  {
    "id": "1ea52717-28a8-4f24-8eec-fb3540fcd241",
    "text": "How would you rate your stress level today?",
      "answer": "10"
  },
  {
    "id": "7daf2bae-9640-4b62-bf9f-2968a1a80baf",
    "text": "Did you have trouble sleeping last night?",
      "answer": rectangleCheckboxchecked +""
  },
  {
    "id": "fa90cf43-3cc0-404c-a0e9-24122b99bce8",
    "text": "Did you consume any sugary or high-fat foods or drinks?",
      "answer": rectangleCheckbox1checked +""
  },
  {
    "id": "a5c4251f-dff6-4876-a1d7-573d90ba01b8",
    "text": "Did you consume enough fruits and vegetables?",
      "answer": rectangleCheckbox2checked +""
  },
  {
    "id": "a8e6e7e7-6190-4a95-9987-050d89c7d841",
    "text": "Did you engage in any activities that helped you relax or reduce stress?",
      "answer": rectangleCheckbox3checked +""
  },
  {
    "id": "a8e6e7e7-6190-4a95-9987-050d89c7d841",
    "text": "Have you noticed any changes in your appetite or dietary habits recently?",
      "answer": rectangleCheckbox4checked+"" 
  }];

  if(schema!=null && schema["questions"]!=null){
  for(var i=0;i<data.length;i++ ){
 var id= data[i]["id"];
    for(var j=0;j<8;j++ ){
        if(data[i]["text"]===schema["questions"][i]["text"]){
          id=schema["questions"][i]["id"];
        }
    }
    data[i]["id"]=id;
  }
}
    return {"surveyId": "6473cbb0ea3d2a731c87ed1b","userId": "64720d3c674ea050a7ef559d", "answers":data};
  }
  const sendSurvey = async () => {
    
    var body=getStructureSurvey();
    
      const options = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(body)
      };
      const response = await fetch('http://192.168.219.23:8090/survey/response', options);

      if (response.ok) {
        console.log('File sent to server');
        createTwoButtonAlert();
        //navigation.navigate("Home");
      } else {

        console.error('Failed to send file to server');
      }
    
  };
  return (
    <View style={styles.survey}>
      <View style={styles.groupParent}>
        <View style={styles.parentLayout1}>
          <Text style={styles.dailySurvey}>Daily Survey</Text>
          <Pressable
          onPress={() => sendSurvey()}
        >
 <Image
            style={[styles.image1Icon, styles.containerLayout]}
            contentFit="cover"
            source={require("../assets/image-1.png")}
          />
        </Pressable>
         
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
