import { motion } from 'framer-motion';
import React from 'react';

export const Confetti = ({ active }) => (
  active ? (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      exit={{ opacity: 0 }}
      className="fixed inset-0 pointer-events-none"
    >
      {Array.from({ length: 50 }).map((_, i) => (
        <motion.div
          key={i}
          className="absolute w-2 h-2 bg-indigo-500"
          initial={{
            x: '50vw',
            y: '50vh',
            scale: 0
          }}
          animate={{
            x: `${Math.random() * 100}vw`,
            y: `${Math.random() * 100}vh`,
            scale: [0, 1, 0],
            rotate: [0, 360]
          }}
          transition={{
            duration: 1.5,
            delay: Math.random() * 0.2,
            repeat: Infinity
          }}
        />
      ))}
    </motion.div>
  ) : null
);
