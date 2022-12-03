import { Redirect, Route } from 'react-router-dom';
import {
  IonApp,
  IonIcon,
  IonLabel,
  IonRouterOutlet,
  IonTabBar,
  IonTabButton,
  IonTabs,
  setupIonicReact
} from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import { ellipse, square, triangle } from 'ionicons/icons';
import SignIn from './pages/SignIn';
import SignUp from './pages/SignUp';
import Feed from './pages/Feed';

/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';

/* Theme variables */
import './theme/variables.css';
import { useState } from 'react';
import Task from './pages/Task';
import Profile from './pages/Profile';

setupIonicReact();

const App: React.FC = () => {
  const [signedIn, setSignedIn] = useState(false);
  // eslint-disable-next-line
  const [uid, setUid] = useState<string | null>(null);

  return (
    <IonApp>
      <IonReactRouter>
        <IonTabs>
          <IonRouterOutlet>
            <Route exact path="/">
              <Redirect to="/signin" />
            </Route>
            <Route exact path="/signin">
              <SignIn setSignedIn={setSignedIn} setUid={setUid} />
            </Route>
            <Route exact path="/signup">
              <SignUp />
            </Route>
            <Route exact path="/feed">
              <Feed uid={uid} />
            </Route>
            <Route exact path="/task">
              <Task />
            </Route>
            <Route exact path="/profile">
              <Profile />
            </Route>
          </IonRouterOutlet>
          <IonTabBar hidden={!signedIn} slot="bottom">
            <IonTabButton tab="feed" href="/feed">
              <IonIcon icon={triangle} />
              <IonLabel>Feed</IonLabel>
            </IonTabButton>
            <IonTabButton tab="task" href="/task">
              <IonIcon icon={ellipse} />
              <IonLabel>Task</IonLabel>
            </IonTabButton>
            <IonTabButton tab="profile" href="/profile">
              <IonIcon icon={square} />
              <IonLabel>Profile</IonLabel>
            </IonTabButton>
          </IonTabBar>
        </IonTabs>
      </IonReactRouter>
    </IonApp>
  );
};

export default App;
