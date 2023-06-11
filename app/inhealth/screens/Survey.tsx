import React, { useEffect, useState } from 'react';
import { View, Text, TouchableOpacity, ScrollView, StyleSheet, Dimensions, Alert } from 'react-native';
import Constants from 'expo-constants';
import Menu from '../components/Menu';
import { useNavigation } from "@react-navigation/native";
import AsyncStorage from '@react-native-async-storage/async-storage';


const { width, height } = Dimensions.get('window');

const QuestionScreen: React.FC = () => {
  const [questions, setQuestions] = useState([]);
  const [selectedAnswers, setSelectedAnswers] = useState([]);
  const navigation = useNavigation();
  const [idu, setIdU] = useState(false);  
  const [id, setId] = useState(Constants.installationId);
  useEffect(() => {
    if(!idu){
      setIdU(true);
    AsyncStorage.getItem('ID')
    .then(value => setId(value))
    .catch(error => console.log('Error getting ID from local storage:', error));
    }
  }, []);

  useEffect(() => {
    fetchQuestions();
  }, []);

  const fetchQuestions = async () => {
    try {
      const response = await fetch('http://192.168.0.22:8090/question/health');
      const data = await response.json();
      setQuestions(data);
    } catch (error) {
      console.log('Error fetching questions:', error);
    }
  };

  const handleAnswerSelection = (answerId: string, answer: string, text: string) => {
    const currentDate = new Date();
    const formattedDate = currentDate.toISOString().split('T')[0];
    console.log(formattedDate);

    const updatedAnswers = selectedAnswers.filter((answer) => answer.id !== answerId);
    updatedAnswers.push({ id: answerId, answer: answer, date: formattedDate, text: text });
    setSelectedAnswers(updatedAnswers);
  };

  const handleSaveAnswers = async () => {
    try {
      console.log(id);
      console.log('http://192.168.0.22:8090/question/answers?person=' + id);

      const response = await fetch('http://192.168.0.22:8090/question/answers?person=' + id, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(selectedAnswers),
      })
      .then(() => {
        // Alert success message
        Alert.alert("Survey Sent", "Your survey has been successfully sent!");
        // Clear selected answers
        setSelectedAnswers([]);
        navigation.navigate("Home");
      });

      console.log(Constants.installationId)
      console.log('Request Body:', JSON.stringify(selectedAnswers));

      // Handle response as needed
    } catch (error) {
      Alert.alert("Error", "Failed to send the survey. Please try again.");
      console.log('Error saving answers:', error);
    }
  };

  return (
   
 
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.title}>Health Survey</Text>
      {questions.map((question: any) => (
        <View key={question.healthQuestion.id} style={styles.questionContainer}>
          <Text style={styles.questionText}>{question.healthQuestion.text}</Text>
          {question.answers.map((answer: any) => (
            <TouchableOpacity
              key={answer.id}
              onPress={() => handleAnswerSelection(answer.id, answer.answer, answer.text)}
              style={[
                styles.answerButton,
                selectedAnswers.find((selected) => selected.id === answer.id)
                  ? styles.selectedAnswerButton
                  : null,
              ]}
            >
              <Text style={styles.answerButtonText}>{answer.text}</Text>
            </TouchableOpacity>
          ))}
        </View>
      ))}
      <TouchableOpacity onPress={handleSaveAnswers} style={styles.saveButton}>
        <Text style={styles.saveButtonText}>Save Answers</Text>
      </TouchableOpacity>
    </ScrollView>

    
  );
};

const styles = StyleSheet.create({
  profile: {
    flex: 1,
    height: 812,
    width: "100%",
  },
  notification1Parent: {
    top: 700,
    position: "absolute",

  },
  container: {
    flexGrow: 1,
    padding: 16,
    minHeight: height,
  },
  title: {
    marginTop: 40,
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 16,
  },
  questionContainer: {
    marginBottom: 16,
  },
  questionText: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  answerButton: {
    backgroundColor: '#eaeaea',
    padding: 8,
    borderRadius: 8,
    marginBottom: 8,
  },
  selectedAnswerButton: {
    backgroundColor: '#3b82f6',
  },
  answerButtonText: {
    fontSize: 14,
    color: '#333',
  },
  saveButton: {
    backgroundColor: '#3b82f6',
    padding: 12,
    borderRadius: 8,
    alignItems: 'center',
    marginTop: 16,
  },
  saveButtonText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#fff',
  },
});

export default QuestionScreen;
