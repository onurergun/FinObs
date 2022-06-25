import axios from "axios";

import {ENDPOINT_URL_BASE} from "./ServiceHelper";

class AuthService {
    constructor() {
        this.endpoint_url_base_auth = ENDPOINT_URL_BASE + "auth";
    }

    async login(user) {
        try {
            const body = {
                userName: user.userName,
                password: user.password
            };

            const response = await axios.post(this.endpoint_url_base_auth, body);
            return response;
        } catch (err) {
            console.error(err);
            return err.response;
        }
    }
}

export default new AuthService();