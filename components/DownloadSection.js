import React from 'react';
import {Apple, Download } from 'lucide-react';
import { AnimatedDownloadButtonClient } from './AnimatedDownloadButtonClient';
import { Confetti } from './Confetti';
import { AnimatePresence } from 'framer-motion';

export const DownloadSection = ({ handleDownloadClick, isAnimating, showConfetti }) => (
  <section id="download" className="py-16 sm:py-24">
    <div className="max-w-7xl mx-auto px-4 sm:px-6 text-center">
      <div className="flex flex-col sm:flex-row justify-center gap-4">
        <a href="https://github.com/aniruddha-adhikary/mrt-buddy/releases/latest/download/app-release.apk">
          <AnimatedDownloadButtonClient
            platform="android"
            icon={Download}
            initialText="Download for Android"
            changeText="Downloaded!"
            onClick={() => {handleDownloadClick(); sendGAEvent({ event: 'download', value: 'android' });}}
            isAnimating={isAnimating}
          />
        </a>
        <a href="https://apps.apple.com/app/mrt-buddy/id6737849667">
          <img src="/app_store.svg" alt="Download on the App Store" />
        </a>
      </div>
      <p className="text-gray-600 mt-4">⚠️ While our app is pending Play Store approval, get updates on our WhatsApp channel:</p>
      <a href="https://whatsapp.com/channel/0029VaurMehLI8Yeb3STq42g" className="text-blue-600 hover:text-blue-800 mt-2 inline-block">
        Join WhatsApp Channel
      </a>
      <p className="text-sm text-gray-500 mt-8">
        This is an independent project and is not officially endorsed by or affiliated with Dhaka Mass Transit Company Limited (DMTCL).
      </p>
      <AnimatePresence>
        {showConfetti && <Confetti active={showConfetti} />}
      </AnimatePresence>
    </div>
  </section>
);
