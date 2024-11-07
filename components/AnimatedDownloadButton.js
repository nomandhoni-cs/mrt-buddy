import React, { useState } from 'react';
import AndroidDownloadButton from './AndroidDownloadButton';

const AnimatedDownloadButton = ({ platform, icon: Icon, initialText, changeText, onClick, isAnimating }) => {
  const [isClicked, setIsClicked] = useState(false);

  const handleClick = () => {
    if (!isAnimating) {
      setIsClicked(true);
      onClick?.();
      setTimeout(() => {
        setIsClicked(false);
      }, 2000);
    }
  };

  return (
    <AndroidDownloadButton 
      onClick={handleClick}
      isClicked={isClicked}
    />
  );
};

export default AnimatedDownloadButton;
