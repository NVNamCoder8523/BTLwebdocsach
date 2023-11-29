"use client";
import { useEffect, useState } from "react";
import "../../styles/login.css";
import { useRouter } from "next/navigation";
import axios from "axios";

export default function Auth() {
  interface IUser {
    username: string;
    password: string;
    confirmPassword: string;
  }
  let ArrayData:any = {
    jwt: "",
    username: "",
    email: "",
  }
  if (!localStorage.getItem("login")) {
    localStorage.setItem("login", JSON.stringify(ArrayData));
  }
  
  const [rightPanel, setRightPanel] = useState<any>();
  const [dataUser, setDataUser] = useState<IUser>({
    username: "",
    password: "",
    confirmPassword: "",
  });
  const [msg, setMsg] = useState<string>("");

  const router = useRouter();
  const handleRighPanel = () => {
    setRightPanel("right-panel-active");
  };
  const handleRemovePanel = () => {
    setRightPanel("");
  };
  const handleLogin = async () => {
    const res = await axios.post('http://localhost:8080/api/auth/signin',
    {
      username: dataUser.username,
      password: dataUser.password
    })
    if(res.status == 200){
      ArrayData.jwt = res.data.jwt;
      ArrayData.username = res.data.username
      ArrayData.email=res.data.email;
      localStorage.setItem("login", JSON.stringify(ArrayData));
      router.push('/');
    };
  };
  const handleRegister = async () => {
    if (dataUser.password === dataUser.confirmPassword) {
      try {
        // const res = await axios.post(`http://localhost:8000/api/auth/register`,)
      } catch (error) {
        alert("Tạo mới tài khoản không thành công");
      }
      console.log(dataUser);
    } else {
      console.log("failed");
    }
  };
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDataUser({ ...dataUser, [e.target.name]: e.target.value });
  };
  return (
    <>
      <div className="main_form">
        <div className={`container ${rightPanel}`}>
          <div className="form-container sign-up-container">
            <div className="div_form">
              <h1 className="h_1">Tạo mới tài khoản</h1>
              <input
                type="text"
                placeholder="Tên đăng nhập"
                name="username"
                onChange={handleChange}
                className="input_input"
              />
              <input
                type="password"
                placeholder="Mật khẩu"
                name="password"
                onChange={handleChange}
                className="input_input"
              />
              <input
                type="password"
                placeholder="Nhập lại mật khẩu"
                name="confirmPassword"
                onChange={handleChange}
                className="input_input"
              />
              <button className="button_button" onClick={handleRegister}>
                Submit
              </button>
            </div>
          </div>
          <div className="form-container sign-in-container">
            <div className="div_form">
              <h1 className="h_1" style={{ color: "red" }}>
                {msg}
              </h1>
              <h1 className="h_1">Đăng nhập</h1>
              <span className="span+span">hoặc tạo mới tài khoản</span>
              <input
                type="email"
                placeholder="Tên đăng nhập"
                name="username"
                onChange={handleChange}
                className="input_input"
              />
              <input
                type="password"
                placeholder="Mật khẩu"
                name="password"
                onChange={handleChange}
                className="input_input"
              />
              <p className="p_p">Quên mật khẩu?</p>
              <button
                onClick={() => {
                  handleLogin();
                }}
                className="button_button"
              >
                Submit
              </button>
            </div>
          </div>
          <div className="overlay-container">
            <div className="overlay">
              <div className="overlay-panel overlay-left">
                <h1 className="h_1">Chào mừng bạn trở lại!</h1>
                <p className="p_p">
                  để lưu lại kết kết nối, hãy liên lạc với chúng tôi
                </p>
                <button
                  className="ghost button_button"
                  id="signIn"
                  onClick={handleRemovePanel}
                >
                  Đăng nhập
                </button>
              </div>
              <div className="overlay-panel overlay-right">
                <h1 className="h_1">Hello, Friend!</h1>
                <p className="p_p">
                  Nhập thông tin cá nhân của bạn và bắt đầu hành trình với chúng
                  tôi
                </p>
                <button
                  className="ghost button_button"
                  id="signUp"
                  onClick={handleRighPanel}
                >
                  Đăng ký
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
