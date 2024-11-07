import React from 'react';

export const Iphone15Pro = ({ width = 300, height = 600, src, ...props }) => (
  <svg
    width={width}
    height={height}
    viewBox="0 0 433 882"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <path
      d="M2 73C2 32.6832 34.6832 0 75 0H357C397.317 0 430 32.6832 430 73V809C430 849.317 397.317 882 357 882H75C34.6832 882 2 849.317 2 809V73Z"
      className="fill-[#E5E5E5] dark:fill-[#404040]"
    />
    <path
      d="M6 74C6 35.3401 37.3401 4 76 4H356C394.66 4 426 35.3401 426 74V808C426 846.66 394.66 878 356 878H76C37.3401 878 6 846.66 6 808V74Z"
      className="dark:fill-[#262626] fill-white"
    />
    {src && (
      <image
        href={src}
        x="21.25"
        y="19.25"
        width="389.5"
        height="843.5"
        preserveAspectRatio="xMidYMid slice"
        clipPath="url(#roundedCorners)"
      />
    )}
    <defs>
      <clipPath id="roundedCorners">
        <rect
          x="21.25"
          y="19.25"
          width="389.5"
          height="843.5"
          rx="55.75"
          ry="55.75"
        />
      </clipPath>
    </defs>
  </svg>
);
