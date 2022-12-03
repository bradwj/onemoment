import { IonButton, IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonContent, IonHeader, IonIcon, IonItem, IonLabel, IonPage, IonRadio, IonTitle, IonToggle, IonToolbar, useIonAlert, useIonToast } from '@ionic/react';
import { useEffect, useState } from 'react';
import './Task.css';
import { addCircleOutline, checkmarkCircleOutline } from 'ionicons/icons';
import {
  MediaCapture,
  MediaFile,
  CaptureVideoOptions,
} from '@awesome-cordova-plugins/media-capture';

const POSSIBLE_TASKS = ["Share what you're up to", "Share something or someone that you enjoy"];

const videoOptions: CaptureVideoOptions = { limit: 1, duration: 30 };

const Task: React.FC = () => {

  const [presentAlert] = useIonAlert();
  const [presentToast] = useIonToast();

  const [task, setTask] = useState<string | null>(null);
  const [video, setVideo] = useState<any | null>(null);
  const [sharePublicly, setSharePublicly] = useState<boolean>(true);

  useEffect(() => {
    // set task to random task
    setTask(POSSIBLE_TASKS[Math.floor(Math.random() * POSSIBLE_TASKS.length)]);
  }, []);

  const takeVideo = async () => {
    try {
      const capture: any = await MediaCapture.captureVideo(videoOptions);
      // const videoFile = new File(capture[0].fullPath);
      console.log(capture);
      console.log((capture[0] as MediaFile)?.fullPath);
      const captureData: any = capture[0];
      captureData.localURL = (window as any).Ionic.WebView.convertFileSrc(capture[0].fullPath);
      console.log('file url', captureData.localURL);

      setVideo(captureData);

    } catch (e: any) {
      presentAlert({
        header: 'Error',
        message: `An error occurred: ${JSON.stringify(e)}`,
        buttons: ['OK'],
      });
      console.error(e);
    }
  };

  const submitVideo = async () => {
    console.log('submitting video');
    console.log('sharing publicly?', sharePublicly);
    const resp = await new Promise(resolve => {
      setTimeout(() => {
        resolve('success');
      }, 1000);
    });
    presentToast({
      message: "Uploaded video successfully!",
      duration: 2000,
      icon: checkmarkCircleOutline,
      color: "success",
    });
  };

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Task</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Task</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Today's Task</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            {task}
          </IonCardContent>
        </IonCard>
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Record Video</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            {video === null ?
              <IonButton onClick={takeVideo}>
                <IonIcon icon={addCircleOutline} />
              </IonButton>
              :
              <>
                <video src={video.localURL} controls width="200px" height="auto" />
                <div>
                  <IonButton onClick={takeVideo}>
                    Retake video
                  </IonButton>
                  <IonButton onClick={submitVideo} color="success" size="large" style={{ marginTop: "16px", marginBottom: "10px" }}>
                    Submit task
                  </IonButton>
                  <IonItem>
                    <div>
                      <IonLabel>Share Publicly?</IonLabel>
                      <IonToggle checked={sharePublicly} onIonChange={() => setSharePublicly(!sharePublicly)}></IonToggle>
                    </div>
                  </IonItem>
                </div>
              </>
            }
          </IonCardContent>
        </IonCard>
      </IonContent>
    </IonPage>
  );
};

export default Task;
