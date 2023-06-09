const Stack = createNativeStackNavigator();
import React, { useState, useEffect } from 'react';
import { NavigationContainer } from "@react-navigation/native";
import { useFonts } from "expo-font";
import Intro from "./screens/Intro";
import Disease from "./screens/Disease";
import Statistics from "./screens/Statistics";
import Profile from "./screens/Profile";
import StatisticsIII from "./screens/StatisticsIII";
import Home from "./screens/Home";
import DiseaseII from "./screens/DiseaseII";
import StatisticsII from "./screens/StatisticsII";
import Data from "./screens/Data";
import Survey from "./screens/Survey";
import Welcom from "./screens/Welcom";
import MIcon from "react-native-vector-icons/MaterialCommunityIcons";
import { IconRegistry, ApplicationProvider } from "@ui-kitten/components";
import * as eva from "@eva-design/eva";
import { Gyroscope, Accelerometer } from 'expo-sensors';
import Constants from 'expo-constants';
import { LogBox } from 'react-native';

LogBox.ignoreLogs([
  'Non-serializable values were found in the navigation state',
]);

const SENSOR_TASK_NAME = 'sensorTask';
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import {
  View,
  Text,
  Pressable,
  TouchableOpacity,
  StyleSheet,
} from "react-native";

const App = () => {
  
  const [hideSplashScreen, setHideSplashScreen] = React.useState(true);
  const [fontsLoaded, error] = useFonts({
    "DM Sans_regular": require("./assets/fonts/DM_Sans_regular.ttf"),
    "DM Sans_medium": require("./assets/fonts/DM_Sans_medium.ttf"),
    "DM Sans_bold": require("./assets/fonts/DM_Sans_bold.ttf"),
    Poppins_regular: require("./assets/fonts/Poppins_regular.ttf"),
    Poppins_medium: require("./assets/fonts/Poppins_medium.ttf"),
    Poppins_semibold: require("./assets/fonts/Poppins_semibold.ttf"),
    Montserrat_light: require("./assets/fonts/Montserrat_light.ttf"),
    Montserrat_regular: require("./assets/fonts/Montserrat_regular.ttf"),
    Montserrat_medium: require("./assets/fonts/Montserrat_medium.ttf"),
    Montserrat_semibold: require("./assets/fonts/Montserrat_semibold.ttf"),
    Montserrat_bold: require("./assets/fonts/Montserrat_bold.ttf"),
  });

  const [sensorData, setSensorData] = useState({
    xAccelerometer: 0,
    yAccelerometer: 0,
    zAccelerometer: 0,
    xGyroscope: 0,
    yGyroscope: 0,
    zGyroscope: 0,
  });
  const [fileUri, setFileUri] = useState(null);
  const [data, setData] = useState("");


  useEffect(() => {
    Accelerometer.setUpdateInterval(100000);
    Gyroscope.setUpdateInterval(100000);

    const subscriptionAccelerometer = Accelerometer.addListener(
      (accelerometerData) => {
        setSensorData((prevData) => ({
          ...prevData,
          xAccelerometer: accelerometerData.x,
          yAccelerometer: accelerometerData.y,
          zAccelerometer: accelerometerData.z,
        }));
      }
    );

    const subscriptionGyroscope = Gyroscope.addListener((gyroscopeData) => {
      setSensorData((prevData) => ({
        ...prevData,
        xGyroscope: gyroscopeData.x,
        yGyroscope: gyroscopeData.y,
        zGyroscope: gyroscopeData.z,
      }));
    });

    return () => {
      subscriptionAccelerometer.remove();
      subscriptionGyroscope.remove();
    };
  }, []);

  useEffect(() => {
  
      const writeToFile = async () => {
        console.log("getting data sensors");
        const now = new Date();
        const deviceId = Constants.installationId;
        const newFileContent = `${deviceId},${now.toISOString()},${sensorData.xAccelerometer},${sensorData.yAccelerometer},${sensorData.zAccelerometer},${sensorData.xGyroscope},${sensorData.yGyroscope},${sensorData.zGyroscope}\n`;

        const concatenatedData = data + newFileContent;
         setData(concatenatedData);
      };

      writeToFile();
    
  }, [sensorData]);

  const sendFileToServer = async () => {
    if (fileUri) {
      const formData = new FormData();
      var values=fileUri.split("/");
      var nam=values[values.length-1].replace(".txt","");
      nam=nam.replace(":","-");
      nam=nam.replace(":","-");
      console.log(nam);
      console.log(values);

      formData.append('file', {uri: fileUri, type: "text/plain", name: nam+".txt"});
      formData.append('name', nam);
      setData("");
       
      const options = {
        method: 'POST',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        body: formData
      };
      const response = await fetch('http://192.168.0.22:8090/', options);

      if (response.ok) {
        console.log('File sent to server');
        setFileUri(null);
      } else {

        console.error('Failed to send file to server');
      }
    }
  };

  function MaterialIcon({ name, style }) {
    const { height, tintColor, ...iconStyle } = StyleSheet.flatten(style);
    return (
      <MIcon name={name} size={height} color={tintColor} style={iconStyle} />
    );
  }

  const IconProvider = (name: string | symbol) => ({
    toReactElement: (props: { name: any; style: any; }) => MaterialIcon({ name, ...props }),
  });

  function createIconsMap() {
    return new Proxy(
      {},
      {
        get(target, name) {
          return IconProvider(name);
        },
      }
    );
  }
  const MaterialIconsPack = {
    name: "material",
    icons: createIconsMap(),
  };

  if (!fontsLoaded && !error) {
    return null;
  }

  return (
    <>
      <IconRegistry icons={[MaterialIconsPack]} />
      <ApplicationProvider {...eva} theme={eva.light}>
        <NavigationContainer>
          {hideSplashScreen ? (
            <Stack.Navigator
              initialRouteName="Intro"
              screenOptions={{ headerShown: false }}
            >
              <Stack.Screen
                name="Intro"
                component={Intro}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="Disease"
                component={Disease}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="Statistics"
                component={Statistics}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="Profile"
                component={Profile}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="StatisticsIII"
                component={StatisticsIII}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="Home"
                component={Home}
                initialParams={{fileUri:fileUri, data: data}}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="DiseaseII"
                component={DiseaseII}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="StatisticsII"
                component={StatisticsII}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="Data"
                component={Data}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="Survey"
                component={Survey}
                options={{ headerShown: false }}
              />
              <Stack.Screen
                name="Welcom"
                component={Welcom}
                options={{ headerShown: false }}
              />
            </Stack.Navigator>
          ) : null}
        </NavigationContainer>
      </ApplicationProvider>
    </>
  );
};
export default App;
