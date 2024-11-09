import { motion } from "framer-motion";
import React from "react";

export const GridPattern = ({
  className = "",
  width = 40,
  height = 40,
  x = -1,
  y = -1,
  strokeDasharray = 0,
  numSquares = 200,
  maxOpacity = 0.5,
  duration = 1,
  repeatDelay = 0.5,
}) => (
  <div className={`absolute inset-0 overflow-hidden ${className}`}>
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      transition={{ duration: 0.5 }}
      className="absolute inset-0"
    >
      <svg
        className="absolute w-full h-full"
        width="100%"
        height="100%"
        viewBox={`${x} ${y} ${width * 10} ${height * 10}`}
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        {Array.from({ length: numSquares }).map((_, i) => {
          const row = Math.floor(i / 10);
          const col = i % 10;
          return (
            <motion.rect
              key={i}
              x={col * width}
              y={row * height}
              width={width}
              height={height}
              stroke="currentColor"
              strokeWidth="0.5"
              strokeDasharray={strokeDasharray}
              initial={{ opacity: 0 }}
              animate={{ opacity: Math.random() * maxOpacity }}
              transition={{
                duration,
                repeat: Infinity,
                repeatDelay: Math.random() * repeatDelay,
                ease: "linear",
              }}
              className="text-gray-300"
            />
          );
        })}
      </svg>
    </motion.div>
  </div>
);
