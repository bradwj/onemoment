import { IonButton, IonContent, IonHeader, IonInput, IonItem, IonLabel, IonPage, IonTitle, IonToolbar, useIonToast } from '@ionic/react';
import { checkmarkCircleOutline, closeCircleOutline } from 'ionicons/icons';
import { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { SignInRequest, UserControllerResponse } from '../interfaces';
import './SignIn.css';

interface SignInProps {
  setSignedIn: (signedIn: boolean) => void;
  setUid: (uid: string | null) => void;
}

const SignIn: React.FC<SignInProps> = (props) => {
  const history = useHistory();
  const [presentToast] = useIonToast();

  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleSignInButton = async () => {
    console.log("Signing in with username: " + username + " and password: " + password);
    try {
      const requestBody: SignInRequest = {
        username,
        password
      }
      const resp = await fetch("http://localhost:8080/users/signin", {
        method: "POST",
        headers: {
          'Allow': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody),
      });
      const data: UserControllerResponse = await resp.json();
      console.log("response data:", data);

      if (data?.success) {
        presentToast({
          message: "Signed in successfully!",
          duration: 2000,
          icon: checkmarkCircleOutline,
          color: "success",
        });
        props.setSignedIn(true);
        props.setUid(data.uid);
        // redirect to feed page
        history.push("/feed");
      }
      else {
        throw new Error(data?.message);
      }
    } catch (err: any) {
      presentToast({
        message: `An error occured while attempting to sign in: ${err.message}`,
        duration: 4000,
        icon: closeCircleOutline,
        color: "danger",
      });
    }
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Sign In</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Sign In</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonItem className="ion-margin-bottom">
          <IonLabel>
            Don't have an account?
          </IonLabel>
          <IonLabel slot="end">
            <Link to="/signup">Sign Up here</Link>
          </IonLabel>
        </IonItem>
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
            onIonChange={(e) => setPassword(e.detail.value!)}
          />
        </IonItem>
        <IonButton onClick={handleSignInButton} className="ion-margin-top" expand="block">
          Sign In
        </IonButton>
      </IonContent>
    </IonPage>
  );
};

export default SignIn;
