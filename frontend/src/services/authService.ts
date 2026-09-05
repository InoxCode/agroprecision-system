export interface LoginResponse {
  success: boolean;
  blocked: boolean;
  message: string;
  failedAttempts: number;
}

export async function login(
  email: string,
  password: string
): Promise<LoginResponse> {
  const response = await fetch(
    "http://localhost:8080/api/auth/login",
    {
      method: "POST",

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify({
        email,
        password,
      }),
    }
  );

  const data: LoginResponse = await response.json();

  return data;
}