import { IonButton, IonContent, IonHeader, IonInput, IonItem, IonItemDivider, IonLabel, IonList, IonPage, IonTitle, IonToolbar, useIonToast } from '@ionic/react';
import { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import './SignUp.css';
import { checkmarkCircleOutline, closeCircleOutline } from 'ionicons/icons';

interface User {
  username: string;
  password: string;
  phoneNumber: string;
  birthday: Date;
  biography: string;
}

const SignUp: React.FC = () => {
  const history = useHistory();
  const [presentToast] = useIonToast();

  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [phoneNumber, setPhoneNumber] = useState<string>("");
  const [birthday, setBirthday] = useState<string>("");
  const [biography, setBiography] = useState<string>("");

  const handleSignUpButton = async () => {
    const user: User = {
      username,
      password,
      phoneNumber,
      birthday: new Date(birthday),
      biography
    }
    console.log("Creating new account with user:", user);
    const resp = await fetch("http://localhost:8080/users/create", {
      method: "POST",
      headers: {
        'Allow': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user),
    });
    const data = await resp.json();
    console.log("data:",data);
    if (data) {
      presentToast({
        message: "Account created successfully!",
        duration: 2000,
        icon: checkmarkCircleOutline,
        color: "success",
      });
      // redirect to sign in page
      history.push("/signin");
    }
    else {
      presentToast({
        message: "An error occurred when creating account.",
        duration: 2000,
        icon: closeCircleOutline,
        color: "danger",
      });
    }
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Sign Up</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Sign Up</IonTitle>
          </IonToolbar>
        </IonHeader>
          <IonItem className="ion-margin-bottom">
            <IonLabel>
              Already have an account?
            </IonLabel>
            <IonLabel slot="end">
              <Link to="/signin">Sign In here</Link>
            </IonLabel>
          </IonItem>
        <IonList lines="full">
          <IonItem>
            <IonLabel position="floating">Username</IonLabel>
            <IonInput
              name="username"
              value={username}
              onIonChange={(e) => setUsername(e.detail.value!)}
            />
          </IonItem>
          <IonItem>
            <IonLabel position="floating">Password</IonLabel>
            <IonInput
              name="password"
              type="password" 
              value={password}
              onIonChange={(e) => setPassword(e.detail.value!)}
            />
          </IonItem>
          <IonItem>
            <IonLabel position="floating">Phone Number</IonLabel>
            <IonInput
              name="phone"
              type="tel" 
              placeholder="888-888-8888"
              value={phoneNumber}
              onIonChange={(e) => setPhoneNumber(e.detail.value!)}
            />
          </IonItem>
          <IonItem>
            <IonLabel position="floating">Birthday</IonLabel>
            <IonInput
              name="birthday"
              type="date" 
              value={birthday}
              onIonChange={(e) => setBirthday(e.detail.value!)}
            />
          </IonItem>
          <IonItem>
            <IonLabel position="floating">Biography</IonLabel>
            <IonInput
              name="bio"
              type="text" 
              value={biography}
              onIonChange={(e) => setBiography(e.detail.value!)}
            />
          </IonItem>
        </IonList>
        <IonButton onClick={handleSignUpButton} className="ion-margin-top" expand="block">
          Sign Up
        </IonButton>
      </IonContent>
    </IonPage>
  );
};

export default SignUp;
