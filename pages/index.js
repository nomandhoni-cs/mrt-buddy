import React from "react";
import Head from "next/head";
import { StickyNavbar } from "../components/Navbar";
import dynamic from "next/dynamic";
import { CommunitySection } from '../components/CommunitySection';
import { Footer } from "../components/Footer";
import { Hero } from "../components/Hero";
import { DeviceShowcase } from "../components/DeviceShowcase";
import { Features } from "../components/Features";
import { DownloadSection } from "../components/DownloadSection";

const HomeComponent = () => {
  const [isAnimating, setIsAnimating] = React.useState(false);
  const handleDownloadClick = () => {
    if (!isAnimating) {
      setIsAnimating(true);
      setTimeout(() => {
        setIsAnimating(false);
      }, 2000);
    }
  };

  return (
    <main className="min-h-screen bg-white relative">
      <Head>
        <title>MRT Buddy - Your Dhaka Metro Rail Companion</title>
      </Head>
      <StickyNavbar />
      <CommunitySection />
      <Hero
        handleDownloadClick={handleDownloadClick}
        isAnimating={isAnimating}
      />
      <DeviceShowcase />
      <Features />
      <DownloadSection
        handleDownloadClick={handleDownloadClick}
        isAnimating={isAnimating}
      />
      <Footer />
    </main>
  );
};

// Export the client-side wrapped component
export default dynamic(() => Promise.resolve(HomeComponent), { ssr: false });
