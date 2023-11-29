"use client";

import "../../styles/hotmanga.scss";
import { useEffect, useState } from "react";
import axios from "axios";
import useSWR from "swr";
import { log } from "console";

export default function Test() {
  const [imageUrls, setImageUrls] = useState([null]);

  useEffect(() => {
    const fetchData = async () => {
      const newImageUrls: any[] = [];

      for (let i = 0; i < 4; i++) {
        try {
          const response = await fetch(`http://localhost:8000/image/${i + 1}`);
          const data = await response.json();
          // Assuming your API returns an object with a 'url' property
          newImageUrls.push(data);
        } catch (error) {
          console.error(`Error fetching image ${i + 1}:`, error);
          // Handle errors as needed
        }
      }

      setImageUrls(newImageUrls);
    };
    fetchData();
    // const intervalId = setInterval(() => {
    //   fetchData();
    //   console.log("time");
    // }, 1000); // Call the API every 5 seconds

    // return () => clearInterval(intervalId); // Clear the interval when the component unmounts
  }, []);
  console.log(imageUrls);
  return (
    <>
      <div>
        {imageUrls != null &&
          imageUrls.map((item: any, index: any) => {
            return (
              <div key={index}>
                <img
                  src={`http://localhost:8000/${item?.imagePath}`}
                  alt=""
                  width={100}
                  height={100}
                />
                <br />
              </div>
            );
          })}
      </div>
    </>
  );
}
