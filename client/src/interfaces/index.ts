
export interface User {
  username: string;
  password: string;
  phoneNumber: string;
  birthday: Date;
  biography: string;
}

export interface UserControllerResponse {
  success: boolean;
  uid: string | null;
  message: string;
}

export interface SignInRequest {
  username: string;
  password: string;
}