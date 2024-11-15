import React from "react";
import { motion, AnimatePresence } from "framer-motion";

const tutorials = [
  {
    id: 1,
    title: "Checking Balance",
    description: "Balance of your MRT Pass / Rapid Pass",
    videoId: "mC0_R5OmeQk",
  },
  {
    id: 2,
    title: "Fare Calculator",
    description: "Calculating Fares with MRT Buddy",
    videoId: "Jg2Fl1khdZ8",
  },
  // Add more tutorials as needed
];

export const VideoTutorialsModal = ({ isOpen, onClose }) => {
  if (!isOpen) return null;

  return (
    <AnimatePresence>
      <div className="fixed inset-0 bg-black bg-opacity-50 z-50 flex items-center justify-center p-4">
        <motion.div
          initial={{ opacity: 0, scale: 0.95 }}
          animate={{ opacity: 1, scale: 1 }}
          exit={{ opacity: 0, scale: 0.95 }}
          className="bg-white dark:bg-gray-800 rounded-lg p-6 w-full max-w-2xl max-h-[90vh] overflow-y-auto"
        >
          <h2 className="text-2xl font-bold mb-4 dark:text-white">Video Tutorials</h2>
          
          <div className="space-y-4">
            {tutorials.map((tutorial) => (
              <div 
                key={tutorial.id}
                className="border dark:border-gray-700 rounded-lg p-4"
              >
                <h3 className="font-semibold mb-2 dark:text-white">{tutorial.title}</h3>
                <p className="text-gray-600 dark:text-gray-300 text-sm mb-3">{tutorial.description}</p>
                <div className="aspect-video w-full bg-gray-100 dark:bg-gray-900 rounded-lg overflow-hidden">
                  <iframe
                    width="100%"
                    height="100%"
                    src={`https://www.youtube.com/embed/${tutorial.videoId}`}
                    title={tutorial.title}
                    frameBorder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen
                  ></iframe>
                </div>
              </div>
            ))}
          </div>

          <button
            onClick={onClose}
            className="mt-6 w-full bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-white px-4 py-2 rounded-lg hover:bg-gray-300 dark:hover:bg-gray-600 transition-colors"
          >
            Close
          </button>
        </motion.div>
      </div>
    </AnimatePresence>
  );
};
