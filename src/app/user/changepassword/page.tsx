"use client";
import "../../../styles/user.scss";
import { useEffect, useState } from "react";
import Image from "next/image";
export default function Home() {
  const [pass, setPass] = useState<any>({
    oldPassword: "",
    newPassword: "",
    confirmPassword: "",
  });

  const handleChangePassword = () => {
    console.log("data = ", pass);
    //do something with submit form
  };
  const handleChangeValue = (e: any) => {
    setPass({
      ...pass,
      [e.target.name]: [e.target.value],
    });
  };
  return (
    <>
      <div className="change_container mx-auto">
        <div className="flex items-center justify-center">
          <div>
            <p className="mb-3">Mật khẩu hiện tại</p>
            <p className="mb-3">Mật khẩu mới</p>
            <p>Nhập lại mật khẩu mới</p>
          </div>
          <div className=" ml-3">
            <input
              className="mb-3"
              type="text"
              name="oldPassword"
              onChange={handleChangeValue}
            />
            <br />
            <input
              type="password"
              className="mb-3"
              onChange={handleChangeValue}
              name="newPassword"
            />
            <br />
            <input
              type="password"
              name="confirmPassword"
              onChange={handleChangeValue}
            />
          </div>
        </div>
        <div className="button_submit" onClick={handleChangePassword}>
          submit
        </div>
      </div>
    </>
  );
}
