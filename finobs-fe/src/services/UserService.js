import axios from "axios"
import {ENDPOINT_URL_BASE} from "./ServiceHelper"

class UserService {

    constructor() {
        this.endpoint_url_base_user = ENDPOINT_URL_BASE + "users";
    }

    async createUser(user){
        try {
            const body = {
                firstName: user.firstName,
                lastName: user.lastName,
                userName: user.userName,
                email: user.email,
                password: user.password
            }

            const response = await axios.post(this.endpoint_url_base_user, body);
            return response;
        } catch (err) {
            console.error(err);
            return err.response;
        }
    }
}

export default new UserService();