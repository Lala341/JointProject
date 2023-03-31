import React, { useState, useEffect } from 'react';
import { Text, View, Button } from 'react-native';
import { Gyroscope, Accelerometer } from 'expo-sensors';
import Constants from 'expo-constants';
import * as FileSystem from 'expo-file-system';

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

        console.log(`Data written to file: ${newFileContent}`);

        const fileUri = FileSystem.documentDirectory + filename;
        await FileSystem.writeAsStringAsync(
          fileUri,
          newFileContent,
          { encoding: FileSystem.EncodingType.UTF8 }
        );
        setFileUri(fileUri);
        setIsWriting(false);
      };

      writeToFile();
    }
  }, [sensorData, isWriting]);

  const sendFileToServer = async () => {
    if (fileUri) {
      const response = await fetch('https://jzqa8esvt7.execute-api.us-east-1.amazonaws.com/default/serverInfomationInwealth', {
        method: 'POST',
        headers: {
          'Content-Type': 'text/plain',
        },
        body: FileSystem.readAsStringAsync(fileUri, { encoding: FileSystem.EncodingType.UTF8 })
      });

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