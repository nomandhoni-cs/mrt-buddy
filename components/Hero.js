import React from 'react';
import { motion } from 'framer-motion';
import { GridPattern } from './GridPattern';
import { AnimatedDownloadButtonClient } from './AnimatedDownloadButtonClient';
import { Apple, Download } from 'lucide-react';

export const Hero = ({ handleDownloadClick, isAnimating }) => (
  <section className="relative pt-36 sm:pt-40 pb-16 sm:pb-20 overflow-hidden">
    <GridPattern
      className="opacity-30"
      numSquares={150}
      maxOpacity={0.3}
    />
    <div className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 text-center">
      <div className="max-w-3xl mx-auto mb-12 sm:mb-20">
        <motion.div
          initial={{ letterSpacing: '0em' }}
          animate={{ letterSpacing: '0.1em' }}
          transition={{ duration: 1, ease: 'easeOut' }}
        >
          <h1 className="text-4xl sm:text-5xl lg:text-6xl font-bold tracking-tight bg-clip-text text-transparent bg-gradient-to-r from-gray-900 to-gray-600 mb-4 sm:mb-6">
            Your Smart MRT Companion
          </h1>
        </motion.div>
        <p className="text-lg sm:text-xl text-gray-600 leading-relaxed">
          Manage your MRT cards, check fares, and track your journeys with ease using NFC technology
        </p>
        <div className="flex flex-col sm:flex-row justify-center gap-4 mt-8">
          <a href="https://github.com/aniruddha-adhikary/mrt-buddy/releases/latest/download/app-release.apk">
            <AnimatedDownloadButtonClient
              platform="android"
              icon={Download}
              initialText="Download for Android"
              changeText="Downloaded!"
              onClick={handleDownloadClick}
              isAnimating={isAnimating}
            />
          </a>
          <a href="https://apps.apple.com/app/mrt-buddy/id6737849667">
            <img src="/app_store.svg" alt="Download on the App Store" />
          </a>
        </div>
      </div>
    </div>
  </section>
);
