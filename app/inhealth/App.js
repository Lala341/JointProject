import React, { useState, useEffect } from 'react';
import { Text, View, Button } from 'react-native';
import { Gyroscope, Accelerometer } from 'expo-sensors';
import Constants from 'expo-constants';
import * as FileSystem from 'expo-file-system';
import { encode } from 'querystring'; // Import the querystring module to encode the data
import * as BackgroundFetch from 'react-native-background-fetch';

export default function App() {
  const [sensorData, setSensorData] = useState({
    xAccelerometer: 0,
    yAccelerometer: 0,
    zAccelerometer: 0,
    xGyroscope: 0,
    yGyroscope: 0,
    zGyroscope: 0,
  });
  const [fileUri, setFileUri] = useState(null);
  const [isWriting, setIsWriting] = useState(false);
  const [data, setData] = useState("");



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
      const response = await fetch('http://54.84.181.116:8080/server-0.0.1-SNAPSHOT/', options);

      if (response.ok) {
        console.log('File sent to server');
        setFileUri(null);
      } else {

        console.error('Failed to send file to server');
      }
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>Accelerometer Data:</Text>
      <Text>X: {sensorData.xAccelerometer.toFixed(2)}</Text>
      <Text>Y: {sensorData.yAccelerometer.toFixed(2)}</Text>
      <Text>Z: {sensorData.zAccelerometer.toFixed(2)}</Text>
      <Text>Gyroscope Data:</Text>
      <Text>X: {sensorData.xGyroscope.toFixed(2)}</Text>
      <Text>Y: {sensorData.yGyroscope.toFixed(2)}</Text>
      <Text>Z: {sensorData.zGyroscope.toFixed(2)}</Text>
      <Button title="Send to Server File" onPress={sendFileToServer} disabled={!fileUri} />
    </View>
  );
}