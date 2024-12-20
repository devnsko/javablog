import axios, { AxiosInstance, InternalAxiosRequestConfig } from 'axios';

const apiClient: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
});

// Add a request interceptor
apiClient.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token.replaceAll('"', '')}`;
        }
        return config;
    },
    (error: any) => {
        return Promise.reject(error);
    }
);

export default apiClient;
