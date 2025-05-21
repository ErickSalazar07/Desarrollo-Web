import { Role } from "./role";

export interface UserEntity {
  id:number;
  username:string;
  password:string;
  roles: Role[];
}