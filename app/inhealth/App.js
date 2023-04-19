import React, { useState, useEffect } from 'react';
import { Text, View, Button } from 'react-native';
import { Gyroscope, Accelerometer } from 'expo-sensors';
import Constants from 'expo-constants';
import * as FileSystem from 'expo-file-system';
import { encode } from 'querystring'; // Import the querystring module to encode the data

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

  useEffect(() => {
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
    if (!isWriting) {
      setIsWriting(true);

      const writeToFile = async () => {
        const now = new Date();
        const deviceId = Constants.installationId;
        const filename = `/sensor-data-${now.getFullYear()}-${
          now.getMonth() + 1
        }-${now.getDate()}.txt`;

        const newFileContent = `${deviceId},${now.toISOString()},${sensorData.xAccelerometer},${sensorData.yAccelerometer},${sensorData.zAccelerometer},${sensorData.xGyroscope},${sensorData.yGyroscope},${sensorData.zGyroscope}`;

       // console.log(`Data written to file: ${newFileContent}`);

        const fileUrit = FileSystem.documentDirectory + filename;
        await FileSystem.writeAsStringAsync(
          fileUrit,
          newFileContent,
          { encoding: FileSystem.EncodingType.UTF8 }
        );
        setFileUri(fileUrit);
        setIsWriting(false);
      };

      writeToFile();
    }
  }, [sensorData, isWriting]);

  const sendFileToServer = async () => {
    if (fileUri) {
      const formData = new FormData();
      formData.append('file', {uri: fileUri, type: "text/plain", name: "fileName.txt"});
  
      const options = {
        method: 'POST',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        body: formData
      };
      const response = await fetch('http://172.18.0.8:8080/server-0.0.1-SNAPSHOT/', options);

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