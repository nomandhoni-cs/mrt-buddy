import dynamic from "next/dynamic";
import AnimatedDownloadButton from "./AnimatedDownloadButton";

export const AnimatedDownloadButtonClient = dynamic(
  () => Promise.resolve(AnimatedDownloadButton),
  { ssr: false }
);
