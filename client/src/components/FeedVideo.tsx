import { IonButton, IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonIcon } from "@ionic/react";
import { flagOutline, heart, heartOutline } from "ionicons/icons";
import { useState } from "react";
import { VideoData } from "../interfaces";


const FeedVideo: React.FC<VideoData> = (props) => {

  const [liked, setLiked] = useState(false);

  return (
    <IonCard>
      <IonCardHeader>
        <IonCardTitle>{props.username}</IonCardTitle>
      </IonCardHeader>
      <IonCardContent>
        <video src={props.videoFileURL} controls width="300px" height="auto" style={{ display: "block", marginLeft: "auto", marginRight: "auto" }} />
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <IonButton fill="clear" color="danger" size="large" onClick={() => setLiked(!liked)}>
            <IonIcon icon={liked ? heart : heartOutline} title="Like video" />
          </IonButton>
          <IonButton fill="clear" color="warning" size="large">
            <IonIcon icon={flagOutline} title="Report video" />
          </IonButton>
        </div>
      </IonCardContent>
    </IonCard>
  );
};

export default FeedVideo;