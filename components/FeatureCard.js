import { motion } from "framer-motion";
import React from "react";

export const FeatureCard = ({ feature, index }) => (
  <motion.div
    className="feature-card"
    whileHover={{ scale: 1.02 }}
    initial={{ opacity: 0, y: 20 }}
    animate={{ opacity: 1, y: 0 }}
    transition={{ delay: index * 0.1 }}
  >
    <div className="relative bg-white p-6 rounded-lg">
      <span className="text-4xl mb-4 block">{feature.icon}</span>
      <h3 className="text-xl font-semibold mb-2">{feature.title}</h3>
      <p className="text-gray-600">{feature.description}</p>
    </div>
  </motion.div>
);
