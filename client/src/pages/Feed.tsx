import { IonButton, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonContent, IonHeader, IonIcon, IonItem, IonItemGroup, IonLabel, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import { flagOutline, heartOutline } from 'ionicons/icons';
import { useEffect, useState } from 'react';
import FeedVideo from '../components/FeedVideo';
import { VideoData } from '../interfaces';
import './Feed.css';


interface FeedProps {
  uid: string | null;
}

const Feed: React.FC<FeedProps> = (props) => {

  const [videoFeedData, setVideoFeedData] = useState<VideoData[]>([]);

  useEffect(() => {
    // fetch video feed from API

    // mock data
    const mockData: VideoData[] = [
      {
        username: 'jenny_smith_123',
        videoFileURL: 'https://media.istockphoto.com/id/1267640930/video/young-happy-girl-communicates-online-and-picks-up-her-little-dog.mp4?s=mp4-640x640-is&k=20&c=o1QclQTACDM4mWY2HD003oaLJq9qdQQAbOJi_Bb8viE=',
      },
      {
        username: 'jane_loves_dogs',
        videoFileURL: 'https://media.istockphoto.com/id/1253606799/video/attractive-woman-blogger-speaks-about-professional-voice-over-and-dubbing-she-recording-video.mp4?s=mp4-640x640-is&k=20&c=gPXhB4JvBa9GasDkh8caOKEPtAVPvzrIA1Btqighxnc=',
      },
      {
        username: 'joe.schmoe',
        videoFileURL: 'https://media.istockphoto.com/id/1264876381/video/funny-little-dachshund-wearing-winter-knitted-pullover-and-red-christmas-deer-horns-comes.mp4?s=mp4-640x640-is&k=20&c=nCJUkHxddwq7A1ebS2-7JJusc6x57-PSbMNuyjh6YXU=',
      },
      {
        username: 'ilikefood',
        videoFileURL: 'https://media.istockphoto.com/id/1216828127/video/preparing-mix-salad-with-vegetables-at-home-a-part-of-stirring-the-salad.mp4?s=mp4-640x640-is&k=20&c=_Nct93vMqyer5tlzZFjjx0wRbxwBM2ztGHOn1mqOJvs=',
      }
    ];
    setVideoFeedData(mockData);
  }, [props.uid]);

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Feed</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Feed</IonTitle>
          </IonToolbar>
        </IonHeader>
        {!videoFeedData.length ?
          <IonLabel>
            No video feed data to display.
          </IonLabel>
          :
          <IonItemGroup>
            {videoFeedData.map((videoData: VideoData) => 
              <FeedVideo key={videoData.username} {...videoData} />           )
            }
          </IonItemGroup>
        }
      </IonContent>
    </IonPage>
  );
};

export default Feed;
